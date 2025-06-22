package apisemaperreio.escalante.ajudancia.domain;

import java.time.LocalDateTime;

public record HistoricoAlteracao(
        Long id,
        LocalDateTime dataHora,
        Militar estadoAnterior,
        Militar responsavel) {

}
