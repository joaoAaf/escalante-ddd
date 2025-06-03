package apisemaperreio.escalante.ajudancia.services.useCases;

import apisemaperreio.escalante.ajudancia.domain.entities.Militar;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.MilitarRequest;

public interface MilitarUseCases {

Militar cadastrarMilitar(MilitarRequest militarRequest);   

}
