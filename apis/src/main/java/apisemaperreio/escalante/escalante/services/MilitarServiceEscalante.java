package apisemaperreio.escalante.escalante.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.dtos.MilitarEscalavel;
import apisemaperreio.escalante.escalante.usecases.MilitarUseCasesEscalante;
import apisemaperreio.escalante.escalante.utils.adapters.importador_xlsx.ImportadorMilitaresXLSXAdapter;

@Service
public class MilitarServiceEscalante implements MilitarUseCasesEscalante {

    @Autowired
    private ImportadorMilitaresXLSXAdapter importadorMilitaresXLSXAdapter;

    @Override
    public List<MilitarEscalavel> listarMilitaresEscalaveis(MultipartFile planilhaMilitares) {
        return importadorMilitaresXLSXAdapter.importarMilitaresXLSX(planilhaMilitares);
    }

}
