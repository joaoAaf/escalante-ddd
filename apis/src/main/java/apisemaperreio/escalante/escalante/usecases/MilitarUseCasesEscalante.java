package apisemaperreio.escalante.escalante.usecases;

import java.time.LocalDate;
import java.util.List;

import apisemaperreio.escalante.escalante.domain.Militar;

public interface MilitarUseCasesEscalante {

    List<Militar> listarMilitaresEscalaveis(LocalDate dataInicio, LocalDate dataFim);

}
