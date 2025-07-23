package apisemaperreio.escalante.escalante.utils.adapters;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import apisemaperreio.escalante.escalante.domain.Funcao;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

@Component
public class ExportadorXLSXApachePoi extends Celula implements ExportadorXLSXAdapter {

    @Override
    public void exportarEscalaXLSX(OutputStream outputStream, List<ServicoOperacional> servicos) throws Exception {

        try (var workbook = new XSSFWorkbook()) {
            criarAbaApresentacao(workbook, servicos);
            criarAbaConferencia(workbook, servicos);
            workbook.write(outputStream);
        }
    }

    private void criarAbaApresentacao(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        var dados = new DadosAbaApresentacao(workbook, servicos);
        var estilos = new EstilosPlanilha(workbook);
        
        dados.criarCabecalhoAbaApresentacao(estilos.estiloCabecalho1, estilos.estiloCabecalho2);

        var funcoes = Arrays.stream(Funcao.values())
                .sorted(Comparator.comparingInt(Funcao::getOrdemExibicao))
                .toList();
        for (var funcao : funcoes) {
            int contador = 1;
            dados.linha = dados.planilha.createRow(dados.numeroLinha++);
            incluirNaCelula(criarCelula(dados.linha, estilos.estiloPadrao, 0), funcao.getNome());
            if (funcao.equals(Funcao.OPERADOR_DE_LINHA)) {
                dados.proxLinha = dados.planilha.createRow(dados.numeroLinha++);
                incluirNaCelula(criarCelula(dados.proxLinha, estilos.estiloPadrao, 0), funcao.getNome());
            }
            for (var dataServico : dados.datasServicos) {
                var servico = servicos.stream()
                        .filter(s -> s.getFuncao().equals(funcao) && s.getDataServico().equals(dataServico)).toList();
                if (!servico.isEmpty()) {
                    var militarEscalado = servico.get(0).getMilitar().getPatente().name() + " "
                            + servico.get(0).getMilitar().getNomePaz();
                    incluirNaCelula(criarCelula(dados.linha, estilos.estiloPadrao, contador), militarEscalado);
                    if (servico.size() > 1) {
                        militarEscalado = servico.get(1).getMilitar().getPatente().name() + " "
                                + servico.get(1).getMilitar().getNomePaz();
                        incluirNaCelula(criarCelula(dados.proxLinha, estilos.estiloPadrao, contador), militarEscalado);
                    } else if (funcao.equals(Funcao.OPERADOR_DE_LINHA))
                        incluirNaCelula(criarCelula(dados.proxLinha, estilos.estiloPadrao, contador), "----------------------");
                } else
                    incluirNaCelula(criarCelula(dados.proxLinha, estilos.estiloPadrao, contador), "----------------------");
                if (contador == dados.indiceDataServico)
                    break;
                contador++;
            }
        }
    }

    private void criarAbaConferencia(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        var estilos = new EstilosPlanilha(workbook);
        var dados = new DadosAbaConferencia(workbook);

        dados.criarCabecalhoAbaConferencia(estilos.estiloCabecalho1);

        for (var servico : servicos) {
            dados.linha = dados.planilha.createRow(dados.numeroLinha++);
            dados.escreverLinhasAbaConferencia(estilos.estiloPadrao, servico);
        }
    }

}
