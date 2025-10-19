package apisemaperreio.escalante.escalante.usecases;

import java.util.List;

import apisemaperreio.escalante.escalante.dtos.DadosEscalaRequest;
import apisemaperreio.escalante.escalante.dtos.ServicoOperacionalDto;

public interface EscalaUseCasesEscalante {
    
    List<ServicoOperacionalDto> criarEscalaAutomatica(DadosEscalaRequest request);
    
    
    byte[] exportarEscalaXLSX(List<ServicoOperacionalDto> servicos) throws Exception;

}
