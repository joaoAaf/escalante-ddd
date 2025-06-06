package apisemaperreio.escalante.ajudancia.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apisemaperreio.escalante.ajudancia.dtos.militarescalaveldtos.MilitarEscalavel;
import apisemaperreio.escalante.ajudancia.repositories.MilitarEscalavelRepositoryAjudancia;
import apisemaperreio.escalante.ajudancia.usecases.MilitarEscalavelUseCasesAjudancia;
import apisemaperreio.escalante.ajudancia.utils.MilitarEscalavelMapperAjudancia;

@Service
public class MilitarEscalavelServiceAjudancia implements MilitarEscalavelUseCasesAjudancia {

    @Autowired
    private MilitarEscalavelRepositoryAjudancia militarEscalavelRepository;
    @Autowired
    private MilitarEscalavelMapperAjudancia militarEscalavelMapper;

    @Transactional(readOnly = true)
    @Override
    public List<MilitarEscalavel> listarMilitaresEscalaveis(LocalDate dataInicio, LocalDate dataFim) {
        var militaresEscalaveis = militarEscalavelRepository.buscarMilitaresEscalaveis(dataInicio, dataFim);
        return militarEscalavelMapper.toListMilitarEscalanteDto(militaresEscalaveis);
    }

}
