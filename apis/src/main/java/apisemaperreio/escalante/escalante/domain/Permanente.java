package apisemaperreio.escalante.escalante.domain;

import java.time.LocalDate;
import java.util.Optional;

public class Permanente extends ModernosPrimeiro {

    private final int FOLGA_PERMANENTE = 3;

    public Permanente(LocalDate dataServico) {
        super(dataServico, Funcao.PERMANENTE);
        this.folga = FOLGA_PERMANENTE;
    }

    public Permanente(LocalDate dataServico, ServicoOperacional servicoOperacional) {
        super(dataServico, Funcao.PERMANENTE);
        this.folga = servicoOperacional.getFolga();
        this.militar = servicoOperacional.getMilitar();
    }

    @Override
    public ServicoOperacional cloneDataSeguinte(ServicoOperacional servicoOperacional, Optional<Militar> militar) {
        var proximoServico = militar.orElseThrow().getUltimosServicos().size() % 2 != 0
                ? new AjudanteLinha(servicoOperacional.getDataServico().plusDays(1), servicoOperacional)
                : new Permanente(servicoOperacional.getDataServico().plusDays(1), servicoOperacional);
        if (militar.isEmpty())
            return proximoServico;
        militar.get().getUltimosServicos().add(proximoServico);
        return proximoServico;
    }

}
