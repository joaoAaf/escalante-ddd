package apisemaperreio.escalante.escalante.utils.adapters.exportador_xlsx;

import java.io.OutputStream;
import java.util.List;

import apisemaperreio.escalante.escalante.dtos.ServicoOperacionalDto;

public interface ExportadorXLSXAdapter {

    void exportarEscalaXLSX(OutputStream outputStream, List<ServicoOperacionalDto> servicos) throws Exception;

}
