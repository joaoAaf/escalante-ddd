package apisemaperreio.escalante.ajudancia.dtos.militarescalaveldtos;

import java.time.LocalDate;

public record MilitarEscalavel(String matricula, String nomePaz, LocalDate nascimento, PatenteMilitarEscalavel patente,
        Integer antiguidade, int folgaEspecial, Boolean cov) {

}
