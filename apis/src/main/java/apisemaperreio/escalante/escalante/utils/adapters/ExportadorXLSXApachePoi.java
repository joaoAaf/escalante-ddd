package apisemaperreio.escalante.escalante.utils.adapters;

import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

@Component
public class ExportadorXLSXApachePoi implements ExportadorXLSXAdapter {

    @Override
    public void exportarEscalaXLSX(OutputStream outputStream, List<ServicoOperacional> servicos) throws Exception {

        try (var workbook = new XSSFWorkbook()) {
            var planilha = workbook.createSheet("Escala");
            int numeroLinha = 0;
            var linha = planilha.createRow(numeroLinha++);

            criarCabecalho(linha);

            for (var servico : servicos) {
                linha = planilha.createRow(numeroLinha++);
                escreverLinhas(linha, servico);
            }

            workbook.write(outputStream);

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
        incluirNaCelula(linha, 0, servico.getDataServico().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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

    private String[] listarDiasSemana() {
        String[] dias = new String[7];
        for (int i = 0; i < 7; i++) {
            dias[i] = DayOfWeek.of(i == 0 ? 7 : i)
                    .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR")).toUpperCase();
        }
        return dias;
    }

}
