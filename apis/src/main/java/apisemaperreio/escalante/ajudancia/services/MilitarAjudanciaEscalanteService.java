package apisemaperreio.escalante.ajudancia.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apisemaperreio.escalante.ajudancia.repositories.MilitarAjudanciaEscalanteRepository;
import apisemaperreio.escalante.ajudancia.utils.MilitarAjudanciaEscalanteMapper;
import apisemaperreio.escalante.sharedcore.ajudancia_escalante.dtos.MilitarAjudanciaEscalante;
import apisemaperreio.escalante.sharedcore.ajudancia_escalante.usecases.MilitarAjudanciaEscalanteUseCases;

@Service
public class MilitarAjudanciaEscalanteService implements MilitarAjudanciaEscalanteUseCases {

    private final MilitarAjudanciaEscalanteRepository militarAjudanciaEscalanteRepository;
    private final MilitarAjudanciaEscalanteMapper militarEscalanteMapper;

    public MilitarAjudanciaEscalanteService(
            final MilitarAjudanciaEscalanteRepository militarRepository,
            final MilitarAjudanciaEscalanteMapper militarEscalanteMapper) {
        this.militarAjudanciaEscalanteRepository = militarRepository;
        this.militarEscalanteMapper = militarEscalanteMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MilitarAjudanciaEscalante> listarMilitaresEscalaveis(LocalDate dataInicio, LocalDate dataFim) {
        return militarEscalanteMapper
                .toListMilitarEscalanteDto(militarAjudanciaEscalanteRepository.buscarMilitaresEscalaveis(dataInicio, dataFim));
    }

}
