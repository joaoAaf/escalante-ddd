package apisemaperreio.escalante.escalante.utils.adapters;

import java.util.List;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public interface ExportadorXLSXAdapter {

    void exportarEscalaExcel(String nomeArquivo, List<ServicoOperacional> servicos) throws Exception;

}
