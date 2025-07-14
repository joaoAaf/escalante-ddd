package apisemaperreio.escalante.escalante.usecases;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.domain.Militar;

public interface MilitarUseCasesEscalante {

    List<Militar> listarMilitaresEscalaveis(MultipartFile planilhaMilitares);

}
