package apisemaperreio.escalante.sharedcore.ajudancia_escalante.usecases;

import java.time.LocalDate;
import java.util.List;

import apisemaperreio.escalante.sharedcore.ajudancia_escalante.dtos.MilitarAjudanciaEscalante;

public interface MilitarAjudanciaEscalanteUseCases {

    List<MilitarAjudanciaEscalante> listarMilitaresEscalaveis(LocalDate dataInicio, LocalDate dataFim);

}
