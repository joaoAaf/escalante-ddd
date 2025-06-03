package apisemaperreio.escalante.ajudancia.services.dtos.requestDtos;

import java.time.LocalDate;

public record MilitarRequest(
        String matricula,
        String cpf,
        NomeRequest nome,
        LocalDate nascimento,
        String sexo,
        TelefoneRequest telefone,
        EmailRequest email,
        EnderecoRequest endereco,
        PatenteRequest patente,
        Integer antiguidade,
        int folgaEspecial,
        Boolean escalavel,
        Boolean cov) {

}
