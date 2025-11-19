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

import apisemaperreio.escalante.escalante.dtos.ServicoOperacionalDto;
import apisemaperreio.escalante.escalante.utils.adapters.importador_xlsx.ImportadorEscalaXLSXAdapter;

@Component
public class ImportadorEscalaXLSXApachePoi implements ImportadorEscalaXLSXAdapter {

    @Override
    public List<ServicoOperacionalDto> importarEscalaXLSX(MultipartFile planilha) {

        List<ServicoOperacionalDto> escala = new ArrayList<>();

        try {
            var workbook = WorkbookFactory.create(planilha.getInputStream());
            var sheet = workbook.getSheetAt(1);

            var rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                var row = rowIterator.next();

                var dataServico = LocalDate.parse(validarCelulas(row.getCell(0)));
                var matricula = validarCelulas(row.getCell(1));
                var nomePaz = validarCelulas(row.getCell(2));
                var patente = validarCelulas(row.getCell(3));
                var antiguidade = Integer.valueOf(validarCelulas(row.getCell(4)));
                var funcao = validarCelulas(row.getCell(5));
                var folga = Integer.valueOf(validarCelulas(row.getCell(6)));

                var servico = new ServicoOperacionalDto(
                        dataServico,
                        matricula,
                        nomePaz,
                        patente,
                        antiguidade,
                        funcao,
                        folga);

                escala.add(servico);

            }

        } catch (Exception e) {
            escala.clear();
            System.out.println(e);
        }

        return escala;

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