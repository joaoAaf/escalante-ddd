package apisemaperreio.escalante.escalante.utils.adapters;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.dtos.MilitarEscalavel;

public interface ImportadorMilitaresXLSXAdapter {

    List<MilitarEscalavel> importarMilitaresXLSX(MultipartFile planilha);

}
