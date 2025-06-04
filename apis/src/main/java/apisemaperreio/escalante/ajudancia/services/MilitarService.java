package apisemaperreio.escalante.ajudancia.services;

import org.springframework.stereotype.Service;

import apisemaperreio.escalante.ajudancia.domain.Militar;
import apisemaperreio.escalante.ajudancia.repositories.MilitarRepository;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.services.useCases.MilitarUseCases;
import apisemaperreio.escalante.ajudancia.utils.MilitarMapper;

@Service
public class MilitarService implements MilitarUseCases {

    private final MilitarRepository militarRepository;
    private final MilitarMapper militarMapper;

    public MilitarService(MilitarRepository militarRepository, MilitarMapper militarMapper) {
        this.militarRepository = militarRepository;
        this.militarMapper = militarMapper;
    }

    @Override
    public Militar cadastrarMilitar(MilitarRequest militarRequest) {
        var militar = militarMapper.toMilitar(militarRequest);
        militar.setFolgaEspecial(
                militar.definirFolgaEspecial(militar.getFolgaEspecial(), militar.getPatente().getFolgaEspecial()));
        return militarRepository.save(militar);
    }

}
