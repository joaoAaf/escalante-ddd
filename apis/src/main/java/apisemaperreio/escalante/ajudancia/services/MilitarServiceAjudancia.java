package apisemaperreio.escalante.ajudancia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.repositories.MilitarRepositoryAjudancia;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.usecases.MilitarUseCasesAjudancia;
import apisemaperreio.escalante.ajudancia.utils.MilitarMapperAjudancia;

@Service
public class MilitarServiceAjudancia implements MilitarUseCasesAjudancia {

    @Autowired
    private MilitarRepositoryAjudancia militarRepository;
    @Autowired
    private MilitarMapperAjudancia militarMapper;

    @Override
    public Militar cadastrarMilitar(MilitarRequest militarRequest) {
        var militar = militarMapper.toMilitar(militarRequest);
        militar.setFolgaEspecial(militar.definirFolgaEspecial(
            militar.getFolgaEspecial(), militar.getPatente().getFolgaEspecial()));
        return militarRepository.save(militar);
    }

}
