package apisemaperreio.escalante.escalante.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;
import apisemaperreio.escalante.escalante.usecases.EscalaUseCasesEscalante;
import apisemaperreio.escalante.escalante.usecases.MilitarUseCasesEscalante;
import apisemaperreio.escalante.escalante.utils.adapters.ExportadorXLSXAdapter;
import apisemaperreio.escalante.escalante.utils.factories.EscalaFactory;

@Service
public class EscalaServiceEscalante implements EscalaUseCasesEscalante {

    @Autowired
    private MilitarUseCasesEscalante militarUseCases;

    @Autowired
    private ExportadorXLSXAdapter exportadorXLSX;
    
    @Override
    public List<ServicoOperacional> criarEscalaAutomatica(DadosEscala dadosEscala) {
        var militares = militarUseCases.listarMilitaresEscalaveis(dadosEscala.dataInicio(), dadosEscala.dataFim());
        var escala = EscalaFactory.criarEscala(dadosEscala);
        escala.preencherEscala(militares);
        return escala.getServicosEscala();
    }

    @Override
    public byte[] exportarEscalaXLSX(List<ServicoOperacional> servicos) throws Exception {
        try (var outputStream = new ByteArrayOutputStream()) {
            exportadorXLSX.exportarEscalaXLSX(outputStream, servicos);
            return outputStream.toByteArray();
        }
    }



}
