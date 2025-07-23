package apisemaperreio.escalante.escalante.utils.adapters.importador_xlsx.apachepoi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.dtos.MilitarEscalavel;
import apisemaperreio.escalante.escalante.utils.adapters.importador_xlsx.ImportadorMilitaresXLSXAdapter;

@Component
public class ImportadorMilitaresXLSXApachePoi implements ImportadorMilitaresXLSXAdapter {

    @Override
    public List<MilitarEscalavel> importarMilitaresXLSX(MultipartFile planilha) {

        List<MilitarEscalavel> militares = new ArrayList<>();

        try {
            var workbook = WorkbookFactory.create(planilha.getInputStream());
            var sheet = workbook.getSheetAt(0);

            var rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                var row = rowIterator.next();

                var antiguidade = Integer.valueOf(validarCelulas(row.getCell(0)));
                var matricula = validarCelulas(row.getCell(1));
                var patente = validarCelulas(row.getCell(2));
                var nomePaz = validarCelulas(row.getCell(3));
                var nascimento = LocalDate.parse(validarCelulas(row.getCell(4)));
                var folgaEspecial = Integer.valueOf(validarCelulas(row.getCell(5)));
                var cov = validarCelulas(row.getCell(6)).equalsIgnoreCase("sim");

                var militar = new MilitarEscalavel(
                        matricula,
                        nomePaz,
                        nascimento,
                        patente,
                        antiguidade,
                        folgaEspecial,
                        cov);

                militares.add(militar);

            }

        } catch (Exception e) {
            militares.clear();
            System.out.println(e);
        }

        return militares;

    }

    private String validarCelulas(Cell cell) {
        return switch (cell.getCellType()) {
            case BLANK -> throw new IllegalArgumentException(
                    String.format("A célula [%d, %d] está vazia", cell.getRowIndex(), cell.getColumnIndex()));
            case ERROR -> throw new IllegalArgumentException(
                    String.format("A célula [%d, %d] contém um erro", cell.getRowIndex(), cell.getColumnIndex()));
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    yield date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                }
                yield BigDecimal.valueOf(cell.getNumericCellValue()).toBigInteger().toString();
            }
            default -> throw new IllegalArgumentException(
                    String.format("A célula [%d, %d] não é do tipo esperado", cell.getRowIndex(),
                            cell.getColumnIndex()));
        };
    }

}