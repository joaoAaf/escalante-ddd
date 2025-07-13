package apisemaperreio.escalante.escalante.dtos;

import java.time.LocalDate;

public record MilitarEscalavel(String matricula, String nomePaz, LocalDate nascimento, String patente,
        Integer antiguidade, int folgaEspecial, Boolean cov) {

}
