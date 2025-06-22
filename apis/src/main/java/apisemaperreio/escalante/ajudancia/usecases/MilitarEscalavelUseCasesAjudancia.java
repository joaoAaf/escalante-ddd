package apisemaperreio.escalante.ajudancia.usecases;

import java.time.LocalDate;
import java.util.List;

import apisemaperreio.escalante.ajudancia.dtos.militarescalaveldtos.MilitarEscalavel;

public interface MilitarEscalavelUseCasesAjudancia {

    List<MilitarEscalavel> listarMilitaresEscalaveis(LocalDate dataInicio, LocalDate dataFim);

}
