package apisemaperreio.escalante.escalante.utils.adapters;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public class DadosAbaConferencia extends Celula {

    protected XSSFSheet planilha;
    protected int numeroLinha;
    protected XSSFRow linha;

    public DadosAbaConferencia(XSSFWorkbook workbook) {
        this.planilha = workbook.createSheet("Conferência");
        this.numeroLinha = 0;
        this.linha = planilha.createRow(this.numeroLinha++);
    }

    public void criarCabecalhoAbaConferencia(XSSFCellStyle estilo) {
        int indiceColuna = 0;
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Data");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Matricula");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Militar Escalado");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Posto/ Graduação");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Antiguidade");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Função");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna), "Folga");
    }

    public void escreverLinhasAbaConferencia(XSSFCellStyle estilo, ServicoOperacional servico) {
        int indiceColuna = 0;
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), servico.getDataServico());
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), servico.getMilitar().getMatricula());
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), servico.getMilitar().getNomePaz());
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), servico.getMilitar().getPatente().getNome());
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), servico.getMilitar().getAntiguidade());
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), servico.getFuncao().getNome());
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna), servico.getFolga());
    }

}
