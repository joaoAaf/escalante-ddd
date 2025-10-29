package apisemaperreio.escalante.escalante.dtos;

import java.time.LocalDate;

public record MilitarEscalavel(
                Integer antiguidade,
                String matricula,
                String patente,
                String nomePaz,
                LocalDate nascimento,
                int folgaEspecial,
                Boolean cov) {

}
