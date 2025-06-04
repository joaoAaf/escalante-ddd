package apisemaperreio.escalante.ajudancia.services.useCases;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.MilitarRequest;

public interface MilitarUseCases {

Militar cadastrarMilitar(MilitarRequest militarRequest);   

}
