package apisemaperreio.escalante.escalante.usecases;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public interface EscalaUseCasesEscalante {
    
    List<ServicoOperacional> criarEscalaAutomatica(DadosEscala dadosEscala, MultipartFile planilhaMilitares);
    
    
    byte[] exportarEscalaXLSX(List<ServicoOperacional> servicos) throws Exception;

}
