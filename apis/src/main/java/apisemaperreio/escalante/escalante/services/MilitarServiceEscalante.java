package apisemaperreio.escalante.escalante.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.domain.Militar;
import apisemaperreio.escalante.escalante.usecases.MilitarUseCasesEscalante;
import apisemaperreio.escalante.escalante.utils.adapters.ImportadorMilitaresXLSXAdapter;
import apisemaperreio.escalante.escalante.utils.mappers.MilitarMapperEscalante;

@Service
public class MilitarServiceEscalante implements MilitarUseCasesEscalante {

    @Autowired
    private ImportadorMilitaresXLSXAdapter importadorMilitaresXLSXAdapter;
    @Autowired
    private MilitarMapperEscalante militarMapper;

    @Override
    public List<Militar> listarMilitaresEscalaveis(MultipartFile planilhaMilitares) {
        var militaresEscalaveis = importadorMilitaresXLSXAdapter.importarMilitaresXLSX(planilhaMilitares);
        return militarMapper.toListMilitar(militaresEscalaveis);
    }

}
