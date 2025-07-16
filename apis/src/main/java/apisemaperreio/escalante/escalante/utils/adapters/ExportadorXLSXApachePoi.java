package apisemaperreio.escalante.escalante.utils.adapters;

import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import apisemaperreio.escalante.escalante.domain.Funcao;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

@Component
public class ExportadorXLSXApachePoi implements ExportadorXLSXAdapter {

    @Override
    public void exportarEscalaXLSX(OutputStream outputStream, List<ServicoOperacional> servicos) throws Exception {

        try (var workbook = new XSSFWorkbook()) {
            criarAbaApresentacao(workbook, servicos);
            criarAbaConferencia(workbook, servicos);
            workbook.write(outputStream);
        }
    }

    private void criarAbaApresentacao(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        var planilha = workbook.createSheet("Apresentação");
        int numeroLinha = 0;
        var linha = planilha.createRow(numeroLinha++);
        var proxLinha = planilha.createRow(numeroLinha++);
        var dias = listarDiasSemana();
        var datasServicos = servicos.stream().map(servico -> servico.getDataServico()).distinct().toList();
        int indiceDataServico = 0;
        for (var dia : dias) {
            if (dias.indexOf(dia) == 0) {
                incluirNaCelula(linha, dias.indexOf(dia), "Atualização");
                incluirNaCelula(proxLinha, dias.indexOf(dia), LocalDate.now());
            }
            var dataString = datasServicos.get(indiceDataServico);
            incluirNaCelula(linha, dias.indexOf(dia) + 1, dia);
            incluirNaCelula(proxLinha, dias.indexOf(dia) + 1, dataString);
            indiceDataServico++;
        }
        linha = planilha.createRow(numeroLinha++);
        var funcoes = Arrays.stream(Funcao.values())
                .sorted(Comparator.comparingInt(Funcao::getOrdemExibicao))
                .toList();
        for (var funcao : funcoes) {
            incluirNaCelula(linha, 0, funcao.getNome());
            int contador = 1;
            for (var dataServico : datasServicos) {
                var servico = servicos.stream()
                        .filter(s -> s.getFuncao().equals(funcao) && s.getDataServico().equals(dataServico)).toList();
                if (!servico.isEmpty()) {
                    var militarEscalado = servico.get(0).getMilitar().getPatente().name() + " "
                            + servico.get(0).getMilitar().getNomePaz();
                    incluirNaCelula(linha, contador, militarEscalado);
                }
                if (servico.size() > 1) {
                    var militarEscalado = servico.get(1).getMilitar().getPatente().name() + " "
                            + servico.get(1).getMilitar().getNomePaz();
                    incluirNaCelula(linha, contador, militarEscalado);
                }
                if (contador == indiceDataServico)
                    break;
                contador++;
            }
            linha = planilha.createRow(numeroLinha++);
            if (funcao.equals(Funcao.OPERADOR_DE_LINHA)) {
                incluirNaCelula(linha, 0, funcao.getNome());
                linha = planilha.createRow(numeroLinha++);
            }
        }
    }

    private void criarAbaConferencia(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        var planilha = workbook.createSheet("Conferência");
        int numeroLinha = 0;
        var linha = planilha.createRow(numeroLinha++);

        criarCabecalho(linha);

        for (var servico : servicos) {
            linha = planilha.createRow(numeroLinha++);
            escreverLinhas(linha, servico);
        }
    }

    private void criarCabecalho(XSSFRow linha) {
        incluirNaCelula(linha, 0, "Data");
        incluirNaCelula(linha, 1, "Matricula");
        incluirNaCelula(linha, 2, "Militar Escalado");
        incluirNaCelula(linha, 3, "Posto/ Graduação");
        incluirNaCelula(linha, 4, "Antiguidade");
        incluirNaCelula(linha, 5, "Função");
        incluirNaCelula(linha, 6, "Folga");
    }

    private void escreverLinhas(XSSFRow linha, ServicoOperacional servico) {
        incluirNaCelula(linha, 0, servico.getDataServico());
        incluirNaCelula(linha, 1, servico.getMilitar().getMatricula());
        incluirNaCelula(linha, 2, servico.getMilitar().getNomePaz());
        incluirNaCelula(linha, 3, servico.getMilitar().getPatente().getNome());
        incluirNaCelula(linha, 4, servico.getMilitar().getAntiguidade());
        incluirNaCelula(linha, 5, servico.getFuncao().getNome());
        incluirNaCelula(linha, 6, servico.getFolga());
    }

    private void incluirNaCelula(XSSFRow linha, int coluna, String valor) {
        var celula = linha.createCell(coluna);
        celula.setCellValue(valor);
    }

    private void incluirNaCelula(XSSFRow linha, int coluna, int valor) {
        var celula = linha.createCell(coluna);
        celula.setCellValue(valor);
    }

    private void incluirNaCelula(XSSFRow linha, int coluna, LocalDate valor) {
        var celula = linha.createCell(coluna);
        celula.setCellValue(valor);
        var cellStyle = linha.getSheet().getWorkbook().createCellStyle();
        cellStyle.setDataFormat(linha.getSheet().getWorkbook().createDataFormat().getFormat("dd/MM/yyyy"));
        celula.setCellStyle(cellStyle);
    }

    private List<String> listarDiasSemana() {
        var dias = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            dias.add(DayOfWeek.of(i == 0 ? 7 : i)
                    .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR")).toUpperCase());
        }
        return dias;
    }

}
