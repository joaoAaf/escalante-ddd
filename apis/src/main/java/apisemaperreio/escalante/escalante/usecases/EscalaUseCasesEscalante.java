package apisemaperreio.escalante.escalante.usecases;

import java.util.List;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;
import apisemaperreio.escalante.escalante.dtos.DadosEscalaRequest;

public interface EscalaUseCasesEscalante {
    
    List<ServicoOperacional> criarEscalaAutomatica(DadosEscalaRequest request);
    
    
    byte[] exportarEscalaXLSX(List<ServicoOperacional> servicos) throws Exception;

}
