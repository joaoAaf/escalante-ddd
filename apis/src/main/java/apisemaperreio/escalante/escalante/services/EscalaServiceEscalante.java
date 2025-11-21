package apisemaperreio.escalante.escalante.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    public byte[] obterPlanilhaModeloEscala() {
        try (var inputStream = getClass().getResourceAsStream("/samples/modelo_importacao_escala.xlsx")) {
            if (inputStream == null)
                throw new RuntimeException("Não foi possível encontrar a planilha modelo.");
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler a planilha modelo de escala.", e);
        }
    }

    @Override
    public List<ServicoOperacionalDto> importarEscalaXLSX(MultipartFile planilhaEscala) {
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
