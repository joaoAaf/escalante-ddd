package apisemaperreio.escalante.escalante.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;
import apisemaperreio.escalante.escalante.dtos.DadosEscalaRequest;
import apisemaperreio.escalante.escalante.usecases.EscalaUseCasesEscalante;
import apisemaperreio.escalante.escalante.utils.adapters.exportador_xlsx.ExportadorXLSXAdapter;
import apisemaperreio.escalante.escalante.utils.factories.EscalaFactory;
import apisemaperreio.escalante.escalante.utils.mappers.MilitarMapperEscalante;

@Service
public class EscalaServiceEscalante implements EscalaUseCasesEscalante {

    @Autowired
    private ExportadorXLSXAdapter exportadorXLSX;

    @Autowired
    private MilitarMapperEscalante militarMapper;

    @Override
    public List<ServicoOperacional> criarEscalaAutomatica(DadosEscalaRequest request) {
        var dadosEscala = new DadosEscala(request.dataInicio(), request.dataFim(), request.diasServico());
        var escala = EscalaFactory.criarEscala(dadosEscala);
        escala.preencherEscala(militarMapper.toListMilitar(request.militares()));
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
