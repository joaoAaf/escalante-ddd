package apisemaperreio.escalante.ajudancia.services.usecases;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.services.dtos.requestdtos.MilitarRequest;

public interface MilitarUseCases {

Militar cadastrarMilitar(MilitarRequest militarRequest);

}
