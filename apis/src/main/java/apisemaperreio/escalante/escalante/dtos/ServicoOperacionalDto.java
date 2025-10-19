package apisemaperreio.escalante.escalante.dtos;

import java.time.LocalDate;

public record ServicoOperacionalDto(LocalDate dataServico, String matricula, String nomePaz, String patente,
        Integer antiguidade, String funcao, int folga) {

}
