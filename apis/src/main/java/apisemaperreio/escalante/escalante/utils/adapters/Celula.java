package apisemaperreio.escalante.escalante.utils.adapters;

import java.time.LocalDate;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Celula {

    protected void incluirNaCelula(XSSFCell celula, String valor) {
        celula.setCellValue(valor);
    }

    protected void incluirNaCelula(XSSFCell celula, int valor) {
        celula.setCellValue(valor);
    }

    protected void incluirNaCelula(XSSFCell celula, LocalDate valor) {
        var formatoData = celula.getCellStyle().copy();
        formatoData.setDataFormat(celula.getRow().getSheet().getWorkbook().createDataFormat().getFormat("dd/MM/yyyy"));
        celula.setCellStyle(formatoData);
        celula.setCellValue(valor);
    }

    protected XSSFCell criarCelula(XSSFRow linha, XSSFCellStyle estilo, int indiceColuna) {
        var celula = linha.createCell(indiceColuna);
        celula.setCellStyle(estilo);
        return celula;
    }

}
