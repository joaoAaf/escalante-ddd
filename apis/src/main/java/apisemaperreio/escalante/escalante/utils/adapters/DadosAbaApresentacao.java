package apisemaperreio.escalante.escalante.utils.adapters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public class DadosAbaApresentacao {

    protected XSSFSheet planilha;
    protected int numeroLinha;
    protected XSSFRow linha;
    protected XSSFRow proxLinha;
    protected List<String> diasSemana;
    protected List<LocalDate> datasServicos;
    protected int indiceDataServico;

    public DadosAbaApresentacao(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        this.planilha = workbook.createSheet("Apresentação");
        this.numeroLinha = 0;
        this.linha = this.planilha.createRow(this.numeroLinha++);
        this.proxLinha = this.planilha.createRow(this.numeroLinha++);
        this.diasSemana = listarDiasSemana();
        this.datasServicos = servicos.stream().map(servico -> servico.getDataServico()).distinct().toList();
        this.indiceDataServico = 0;
    }

    private List<String> listarDiasSemana() {
        var diasSemana = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            diasSemana.add(DayOfWeek.of(i == 0 ? 7 : i)
                    .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR")).toUpperCase());
        }
        return diasSemana;
    }

}
