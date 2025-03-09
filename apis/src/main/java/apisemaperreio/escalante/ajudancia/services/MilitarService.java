package apisemaperreio.escalante.ajudancia.services;

import org.springframework.stereotype.Service;

import apisemaperreio.escalante.ajudancia.domain.entities.Militar;
import apisemaperreio.escalante.ajudancia.repositories.MilitarRepository;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.services.useCases.MilitarUseCases;

@Service
public class MilitarService implements MilitarUseCases {

    private final MilitarRepository militarRepository;

    public MilitarService(MilitarRepository militarRepository) {
        this.militarRepository = militarRepository;
    }

    @Override
    public Militar cadastrarMilitar(MilitarRequest militarRequest) {
        var militar = new Militar(militarRequest);
        return militarRepository.save(militar);        
    }

}
