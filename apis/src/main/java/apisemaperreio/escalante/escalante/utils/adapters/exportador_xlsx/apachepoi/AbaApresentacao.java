package apisemaperreio.escalante.escalante.utils.adapters.exportador_xlsx.apachepoi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisemaperreio.escalante.escalante.domain.Funcao;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public class AbaApresentacao extends Celula {

    private XSSFSheet planilha;
    private int numeroLinha;
    private XSSFRow linha;
    private XSSFRow proxLinha;
    private List<String> diasSemana;
    private List<LocalDate> datasServicos;
    private int indiceDataServico;

    public AbaApresentacao(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        this.planilha = workbook.createSheet("Apresentação");
        this.numeroLinha = 0;
        this.linha = this.planilha.createRow(this.numeroLinha++);
        this.proxLinha = this.planilha.createRow(this.numeroLinha++);
        this.diasSemana = listarDiasSemana();
        this.datasServicos = servicos.stream().map(servico -> servico.getDataServico()).distinct().toList();
        this.indiceDataServico = 0;
    }

    public void criarAbaApresentacao(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        var estilos = new EstilosPlanilha(workbook);

        this.criarCabecalhoAbaApresentacao(estilos.getEstiloCabecalho1(), estilos.getEstiloCabecalho2());

        this.criarLinhasAbaApresentacao(estilos.getEstiloPadrao(), servicos);
    }

    private List<String> listarDiasSemana() {
        var diasSemana = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            diasSemana.add(DayOfWeek.of(i == 0 ? 7 : i)
                    .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR")).toUpperCase());
        }
        return diasSemana;
    }

    private void criarCabecalhoAbaApresentacao(XSSFCellStyle estilo1, XSSFCellStyle estilo2) {
        
        criarColunaInicialCabecalho(estilo1, estilo2);

        for (var dia : this.diasSemana) {
            var indiceDia = this.diasSemana.indexOf(dia);
            var dataServico = this.datasServicos.get(this.indiceDataServico);
            incluirNaCelula(criarCelula(this.linha, estilo1, indiceDia + 1), dia);
            incluirNaCelula(criarCelula(this.proxLinha, estilo2, indiceDia + 1), dataServico);
            this.indiceDataServico++;
        }
    }

    private void criarColunaInicialCabecalho(XSSFCellStyle estilo1, XSSFCellStyle estilo2) {
        if (this.indiceDataServico == 0) {
            incluirNaCelula(criarCelula(this.linha, estilo1, 0), "Data de Criação");
            incluirNaCelula(criarCelula(this.proxLinha, estilo2, 0), LocalDate.now());
        } else {
            incluirNaCelula(criarCelula(this.linha, estilo1, 0), "");
            incluirNaCelula(criarCelula(this.proxLinha, estilo2, 0), "");
        }
    }

    private void criarLinhasAbaApresentacao(XSSFCellStyle estilo, List<ServicoOperacional> servicos) {

        var funcoes = Arrays.stream(Funcao.values())
                .sorted(Comparator.comparingInt(Funcao::getOrdemExibicao))
                .toList();
        for (var funcao : funcoes) {
            int contador = 1;
            this.linha = this.planilha.createRow(this.numeroLinha++);
            incluirNaCelula(criarCelula(this.linha, estilo, 0), funcao.getNome());
            if (funcao.equals(Funcao.OPERADOR_DE_LINHA)) {
                this.proxLinha = this.planilha.createRow(this.numeroLinha++);
                incluirNaCelula(criarCelula(this.proxLinha, estilo, 0), funcao.getNome());
            }
            for (var dataServico : this.datasServicos) {
                var servico = servicos.stream()
                        .filter(s -> s.getFuncao().equals(funcao) && s.getDataServico().equals(dataServico)).toList();
                if (!servico.isEmpty()) {
                    var militarEscalado = servico.get(0).getMilitar().getPatente().name() + " "
                            + servico.get(0).getMilitar().getNomePaz();
                    incluirNaCelula(criarCelula(this.linha, estilo, contador), militarEscalado);
                    if (servico.size() > 1) {
                        militarEscalado = servico.get(1).getMilitar().getPatente().name() + " "
                                + servico.get(1).getMilitar().getNomePaz();
                        incluirNaCelula(criarCelula(this.proxLinha, estilo, contador), militarEscalado);
                    } else if (funcao.equals(Funcao.OPERADOR_DE_LINHA))
                        incluirNaCelula(criarCelula(this.proxLinha, estilo, contador), "----------------------");
                } else
                    incluirNaCelula(criarCelula(this.proxLinha, estilo, contador), "----------------------");
                if (contador == this.indiceDataServico)
                    break;
                contador++;
            }
        }

    }

}
