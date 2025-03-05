package apisemaperreio.escalante.ajudancia.domain.entities;

import java.time.LocalDateTime;

public record HistoricoAlteracao(
        Long id,
        LocalDateTime dataHora,
        Militar estadoAnterior,
        Militar responsavel) {

}
