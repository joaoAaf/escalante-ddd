package apisemaperreio.escalante.escalante.utils.adapters;

import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        incluirNaCelula(linha, 1, "Função");
        incluirNaCelula(linha, 2, "Folga");
        incluirNaCelula(linha, 3, "Matricula Militar");
        incluirNaCelula(linha, 4, "Nome Militar");
        incluirNaCelula(linha, 5, "Patente Militar");
        incluirNaCelula(linha, 6, "Antiguidade Militar");
    }

    private void escreverLinhas(XSSFRow linha, ServicoOperacional servico) {
        incluirNaCelula(linha, 0, servico.getDataServico().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        incluirNaCelula(linha, 1, servico.getFuncao().getNome());
        incluirNaCelula(linha, 2, servico.getFolga());
        incluirNaCelula(linha, 3, servico.getMilitar().getMatricula());
        incluirNaCelula(linha, 4, servico.getMilitar().getNomePaz());
        incluirNaCelula(linha, 5, servico.getMilitar().getPatente().getNome());
        incluirNaCelula(linha, 6, servico.getMilitar().getAntiguidade());
    }

    private void incluirNaCelula(XSSFRow linha, int coluna, String valor) {
        var celula = linha.createCell(coluna);
        celula.setCellValue(valor);
    }

    private void incluirNaCelula(XSSFRow linha, int coluna, int valor) {
        var celula = linha.createCell(coluna);
        celula.setCellValue(valor);
    }

}
