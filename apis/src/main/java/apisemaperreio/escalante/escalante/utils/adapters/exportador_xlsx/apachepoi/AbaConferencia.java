package apisemaperreio.escalante.escalante.utils.adapters.exportador_xlsx.apachepoi;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public class AbaConferencia extends Celula {

    private XSSFSheet planilha;
    private int numeroLinha;
    private XSSFRow linha;

    public AbaConferencia(XSSFWorkbook workbook) {
        this.planilha = workbook.createSheet("Conferência");
        this.numeroLinha = 0;
        this.linha = planilha.createRow(this.numeroLinha++);
    }

    public void criarAbaConferencia(XSSFWorkbook workbook, List<ServicoOperacional> servicos) {
        var estilos = new EstilosPlanilha(workbook);

        this.criarCabecalhoAbaConferencia(estilos.getEstiloCabecalho1());

        for (var servico : servicos) {
            this.linha = this.planilha.createRow(this.numeroLinha++);
            this.criarLinhasAbaConferencia(estilos.getEstiloPadrao(), servico);
        }
    }

    private void criarCabecalhoAbaConferencia(XSSFCellStyle estilo) {
        int indiceColuna = 0;
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Data");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Matricula");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Militar Escalado");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Posto/ Graduação");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Antiguidade");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna++), "Função");
        incluirNaCelula(criarCelula(this.linha, estilo, indiceColuna), "Folga");
    }

    private void criarLinhasAbaConferencia(XSSFCellStyle estilo, ServicoOperacional servico) {
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
