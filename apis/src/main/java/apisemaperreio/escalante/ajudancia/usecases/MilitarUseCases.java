package apisemaperreio.escalante.ajudancia.usecases;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.MilitarRequest;

public interface MilitarUseCases {

Militar cadastrarMilitar(MilitarRequest militarRequest);

}
