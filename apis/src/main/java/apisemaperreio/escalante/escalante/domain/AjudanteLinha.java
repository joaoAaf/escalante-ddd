package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Optional;

public class AjudanteLinha extends ModernosPrimeiro {

    private final int FOLGA_AJUDANTE_LINHA = 3;

    public AjudanteLinha(LocalDate dataServico) {
        super(dataServico, Funcao.AJUDANTE_DE_LINHA);
        this.folga = FOLGA_AJUDANTE_LINHA;
    }

    public AjudanteLinha(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.AJUDANTE_DE_LINHA);
        this.folga = servicoOperacional.getFolga();
        this.militar = servicoOperacional.getMilitar();
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Optional<Militar> militar) {
        var proximoServico = militar.orElseThrow().getUltimosServicos().size() % 2 != 0
                ? new Permanente(servicoOperacional.getDataServico().plusDays(1), servicoOperacional)
                : new AjudanteLinha(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
