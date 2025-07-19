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

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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
            var estilo = workbook.createCellStyle();
            if (dias.indexOf(dia) == 0) {
                var celula = linha.createCell(dias.indexOf(dia));
                incluirNaCelula(celula, "Data de Criação");
                definirEstiloCabecalho(celula, estilo);
                celula = proxLinha.createCell(dias.indexOf(dia));
                incluirNaCelula(celula, estilo, LocalDate.now());
                definirEstiloCabecalho(celula, estilo);
            }
            var data = datasServicos.get(indiceDataServico);
            var celula = linha.createCell(dias.indexOf(dia) + 1);
            incluirNaCelula(celula, dia);
            definirEstiloCabecalho(celula, estilo);
            celula = proxLinha.createCell(dias.indexOf(dia) + 1);
            incluirNaCelula(celula, estilo, data);
            definirEstiloCabecalho(celula, estilo);
            indiceDataServico++;
        }
        linha = planilha.createRow(numeroLinha++);
        var funcoes = Arrays.stream(Funcao.values())
                .sorted(Comparator.comparingInt(Funcao::getOrdemExibicao))
                .toList();
        for (var funcao : funcoes) {
            var celula = linha.createCell(0);
            incluirNaCelula(celula, funcao.getNome());
            int contador = 1;
            for (var dataServico : datasServicos) {
                var servico = servicos.stream()
                        .filter(s -> s.getFuncao().equals(funcao) && s.getDataServico().equals(dataServico)).toList();
                if (!servico.isEmpty()) {
                    var militarEscalado = servico.get(0).getMilitar().getPatente().name() + " "
                            + servico.get(0).getMilitar().getNomePaz();
                    celula = linha.createCell(contador);
                    incluirNaCelula(celula, militarEscalado);
                }
                if (servico.size() > 1) {
                    var militarEscalado = servico.get(1).getMilitar().getPatente().name() + " "
                            + servico.get(1).getMilitar().getNomePaz();
                    celula = linha.createCell(contador);
                    incluirNaCelula(celula, militarEscalado);
                }
                if (contador == indiceDataServico)
                    break;
                contador++;
            }
            linha = planilha.createRow(numeroLinha++);
            if (funcao.equals(Funcao.OPERADOR_DE_LINHA)) {
                celula = linha.createCell(0);
                incluirNaCelula(celula, funcao.getNome());
                linha = planilha.createRow(numeroLinha++);
            }
        }
    }

    private List<String> listarDiasSemana() {
        var dias = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            dias.add(DayOfWeek.of(i == 0 ? 7 : i)
                    .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR")).toUpperCase());
        }
        return dias;
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
        var estilo = linha.getSheet().getWorkbook().createCellStyle();
        int indiceColuna = 0;
        var celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, "Data");
        definirEstiloCabecalho(celula, estilo);
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, "Matricula");
        definirEstiloCabecalho(celula, estilo);
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, "Militar Escalado");
        definirEstiloCabecalho(celula, estilo);
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, "Posto/ Graduação");
        definirEstiloCabecalho(celula, estilo);
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, "Antiguidade");
        definirEstiloCabecalho(celula, estilo);
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, "Função");
        definirEstiloCabecalho(celula, estilo);
        celula = linha.createCell(indiceColuna);
        incluirNaCelula(celula, "Folga");
        definirEstiloCabecalho(celula, estilo);
    }

    private void escreverLinhas(XSSFRow linha, ServicoOperacional servico) {
        var estilo = linha.getSheet().getWorkbook().createCellStyle();
        int indiceColuna = 0;
        var celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, estilo, servico.getDataServico());
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, servico.getMilitar().getMatricula());
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, servico.getMilitar().getNomePaz());
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, servico.getMilitar().getPatente().getNome());
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, servico.getMilitar().getAntiguidade());
        celula = linha.createCell(indiceColuna++);
        incluirNaCelula(celula, servico.getFuncao().getNome());
        celula = linha.createCell(indiceColuna);
        incluirNaCelula(celula, servico.getFolga());
    }

    private void incluirNaCelula(XSSFCell celula, String valor) {
        celula.setCellValue(valor);
    }

    private void incluirNaCelula(XSSFCell celula, int valor) {
        celula.setCellValue(valor);
    }

    private void incluirNaCelula(XSSFCell celula, XSSFCellStyle estilo, LocalDate valor) {
        celula.setCellValue(valor);
        estilo.setDataFormat(celula.getRow().getSheet().getWorkbook().createDataFormat().getFormat("dd/MM/yyyy"));
        celula.setCellStyle(estilo);
    }

    private void definirEstiloCabecalho(XSSFCell celula, XSSFCellStyle estilo) {
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        estilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        celula.setCellStyle(estilo);
    }

}
