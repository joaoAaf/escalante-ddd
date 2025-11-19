package apisemaperreio.escalante.escalante.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.dtos.DadosEscalaRequest;
import apisemaperreio.escalante.escalante.dtos.ServicoOperacionalDto;
import apisemaperreio.escalante.escalante.usecases.EscalaUseCasesEscalante;
import apisemaperreio.escalante.escalante.utils.adapters.exportador_xlsx.ExportadorXLSXAdapter;
import apisemaperreio.escalante.escalante.utils.adapters.importador_xlsx.ImportadorEscalaXLSXAdapter;
import apisemaperreio.escalante.escalante.utils.factories.EscalaFactory;
import apisemaperreio.escalante.escalante.utils.mappers.MilitarMapperEscalante;
import apisemaperreio.escalante.escalante.utils.mappers.ServicoOperacionalMapper;

@Service
public class EscalaServiceEscalante implements EscalaUseCasesEscalante {

    @Autowired
    private ImportadorEscalaXLSXAdapter importadorXLSX;

    @Autowired
    private ExportadorXLSXAdapter exportadorXLSX;

    @Autowired
    private MilitarMapperEscalante militarMapper;

    @Autowired
    private ServicoOperacionalMapper servicoOperacionalMapper;

    @Override
    public List<ServicoOperacionalDto> listarEscalaAnterior(MultipartFile planilhaEscala) {
        return importadorXLSX.importarEscalaXLSX(planilhaEscala);
    }

    @Override
    public List<ServicoOperacionalDto> criarEscalaAutomatica(DadosEscalaRequest request) {
        var dadosEscala = new DadosEscala(request.dataInicio(), request.dataFim(), request.diasServico());
        var escala = EscalaFactory.criarEscala(dadosEscala);
        escala.preencherEscala(militarMapper.toListMilitar(request.militares()));
        return servicoOperacionalMapper.toListServicoOperacionalDto(escala.getServicosEscala());
    }

    @Override
    public byte[] exportarEscalaXLSX(List<ServicoOperacionalDto> servicos) throws Exception {
        try (var outputStream = new ByteArrayOutputStream()) {
            exportadorXLSX.exportarEscalaXLSX(outputStream, servicos);
            return outputStream.toByteArray();
        }
    }

}
