package apisemaperreio.escalante.escalante.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apisemaperreio.escalante.ajudancia.usecases.MilitarEscalavelUseCasesAjudancia;
import apisemaperreio.escalante.escalante.domain.Militar;
import apisemaperreio.escalante.escalante.usecases.MilitarUseCasesEscalante;
import apisemaperreio.escalante.escalante.utils.MilitarMapperEscalante;

@Service
public class MilitarServiceEscalante implements MilitarUseCasesEscalante {

    @Autowired
    private MilitarEscalavelUseCasesAjudancia militarEscalavelUseCases;
    @Autowired
    private MilitarMapperEscalante militarMapper;

    @Override
    public List<Militar> listarMilitaresEscalaveis(LocalDate dataInicio, LocalDate dataFim) {
        var militaresEscalaveis = militarEscalavelUseCases.listarMilitaresEscalaveis(dataInicio, dataFim);
        return militarMapper.toListMilitar(militaresEscalaveis);
    }

}
