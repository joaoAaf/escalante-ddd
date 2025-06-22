package apisemaperreio.escalante.escalante.usecases;

import java.util.List;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;

public interface EscalaUseCasesEscalante {
    
    List<ServicoOperacional> criarEscalaAutomatica(DadosEscala dadosEscala);
    
    
    byte[] exportarEscalaXLSX(List<ServicoOperacional> servicos) throws Exception;

}
