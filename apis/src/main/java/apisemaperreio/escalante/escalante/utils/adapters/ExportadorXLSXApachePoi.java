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
            var estiloCabecalho1 = definirEstiloCabecalho1(workbook);
            var estiloCabecalho2 = definirEstiloCabecalho2(workbook);
            var indiceDia = dias.indexOf(dia);
            if (dias.indexOf(dia) == 0) {
                incluirNaCelula(linha.createCell(indiceDia), estiloCabecalho1, "Data de Criação");
                incluirNaCelula(proxLinha.createCell(indiceDia), estiloCabecalho2, LocalDate.now());
            }
            var data = datasServicos.get(indiceDataServico);
            incluirNaCelula(linha.createCell(indiceDia + 1), estiloCabecalho1, dia);
            incluirNaCelula(proxLinha.createCell(indiceDia + 1), estiloCabecalho2, data);
            indiceDataServico++;
        }
        linha = planilha.createRow(numeroLinha++);
        var funcoes = Arrays.stream(Funcao.values())
                .sorted(Comparator.comparingInt(Funcao::getOrdemExibicao))
                .toList();
        for (var funcao : funcoes) {
            var estiloPadrao = definirEstiloPadrao(workbook);
            incluirNaCelula(linha.createCell(0), estiloPadrao, funcao.getNome());
            int contador = 1;
            for (var dataServico : datasServicos) {
                var servico = servicos.stream()
                        .filter(s -> s.getFuncao().equals(funcao) && s.getDataServico().equals(dataServico)).toList();
                if (!servico.isEmpty()) {
                    var militarEscalado = servico.get(0).getMilitar().getPatente().name() + " "
                            + servico.get(0).getMilitar().getNomePaz();
                    incluirNaCelula(linha.createCell(contador), estiloPadrao, militarEscalado);
                }
                if (servico.size() > 1) {
                    var militarEscalado = servico.get(1).getMilitar().getPatente().name() + " "
                            + servico.get(1).getMilitar().getNomePaz();
                    incluirNaCelula(linha.createCell(contador), estiloPadrao, militarEscalado);
                }
                if (contador == indiceDataServico)
                    break;
                contador++;
            }
            linha = planilha.createRow(numeroLinha++);
            if (funcao.equals(Funcao.OPERADOR_DE_LINHA)) {
                incluirNaCelula(linha.createCell(0), estiloPadrao, funcao.getNome());
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
        var estilo = definirEstiloCabecalho1(linha.getSheet().getWorkbook());
        int indiceColuna = 0;
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, "Data");
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, "Matricula");
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, "Militar Escalado");
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, "Posto/ Graduação");
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, "Antiguidade");
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, "Função");
        incluirNaCelula(linha.createCell(indiceColuna), estilo, "Folga");
    }

    private void escreverLinhas(XSSFRow linha, ServicoOperacional servico) {
        var estilo = definirEstiloPadrao(linha.getSheet().getWorkbook());
        int indiceColuna = 0;
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, servico.getDataServico());
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, servico.getMilitar().getMatricula());
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, servico.getMilitar().getNomePaz());
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, servico.getMilitar().getPatente().getNome());
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, servico.getMilitar().getAntiguidade());
        incluirNaCelula(linha.createCell(indiceColuna++), estilo, servico.getFuncao().getNome());
        incluirNaCelula(linha.createCell(indiceColuna), estilo, servico.getFolga());
    }

    private void incluirNaCelula(XSSFCell celula, XSSFCellStyle estilo, String valor) {
        celula.setCellStyle(estilo);
        celula.setCellValue(valor);
    }

    private void incluirNaCelula(XSSFCell celula, XSSFCellStyle estilo, int valor) {
        celula.setCellStyle(estilo);
        celula.setCellValue(valor);
    }

    private void incluirNaCelula(XSSFCell celula, XSSFCellStyle estilo, LocalDate valor) {
        celula.setCellValue(valor);
        var formatoData = estilo.copy();
        formatoData.setDataFormat(celula.getRow().getSheet().getWorkbook().createDataFormat().getFormat("dd/MM/yyyy"));
        celula.setCellStyle(formatoData);
    }

    private XSSFCellStyle definirEstiloCabecalho2(XSSFWorkbook workbook) {
        var estilo = definirEstiloCabecalho1(workbook);
        estilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        return estilo;
    }

    private XSSFCellStyle definirEstiloCabecalho1(XSSFWorkbook workbook) {
        var estilo = definirEstiloPadrao(workbook);
        estilo.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return estilo;
    }

    private XSSFCellStyle definirEstiloPadrao(XSSFWorkbook workbook) {
        var estilo = workbook.createCellStyle();
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setVerticalAlignment(VerticalAlignment.CENTER);
        return estilo;
    }

}
