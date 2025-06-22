package apisemaperreio.escalante.escalante.utils.adapters;

import java.io.OutputStream;
import java.util.List;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public interface ExportadorXLSXAdapter {

    void exportarEscalaXLSX(OutputStream outputStream, List<ServicoOperacional> servicos) throws Exception;

}
