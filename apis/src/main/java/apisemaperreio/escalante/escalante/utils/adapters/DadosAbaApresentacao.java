package apisemaperreio.escalante.escalante.utils.adapters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public class DadosAbaApresentacao extends Celula {

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

    public void criarCabecalhoAbaApresentacao(XSSFCellStyle estilo1, XSSFCellStyle estilo2) {
        
        definirColuna0Cabecalho(estilo1, estilo2);

        for (var dia : this.diasSemana) {
            var indiceDia = this.diasSemana.indexOf(dia);
            var dataServico = this.datasServicos.get(this.indiceDataServico);
            incluirNaCelula(criarCelula(this.linha, estilo1, indiceDia + 1), dia);
            incluirNaCelula(criarCelula(this.proxLinha, estilo2, indiceDia + 1), dataServico);
            this.indiceDataServico++;
        }
    }

    private void definirColuna0Cabecalho(XSSFCellStyle estilo1, XSSFCellStyle estilo2) {
        if (this.indiceDataServico == 0) {
            incluirNaCelula(criarCelula(this.linha, estilo1, 0), "Data de Criação");
            incluirNaCelula(criarCelula(this.proxLinha, estilo2, 0), LocalDate.now());
        } else {
            incluirNaCelula(criarCelula(this.linha, estilo1, 0), "");
            incluirNaCelula(criarCelula(this.proxLinha, estilo2, 0), "");
        }
    }

}
