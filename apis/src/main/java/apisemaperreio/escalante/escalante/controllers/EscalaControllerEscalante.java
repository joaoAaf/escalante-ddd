package apisemaperreio.escalante.escalante.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apisemaperreio.escalante.escalante.domain.ServicoOperacional;
import apisemaperreio.escalante.escalante.dtos.DadosEscalaRequest;
import apisemaperreio.escalante.escalante.usecases.EscalaUseCasesEscalante;

@RequestMapping("/escala")
@RestController
public class EscalaControllerEscalante {

    @Autowired
    private EscalaUseCasesEscalante escalaUseCases;

    @PostMapping
    public ResponseEntity<?> criarEscalaAutomatica(@RequestBody DadosEscalaRequest request) {
        try {
            return ResponseEntity.ok(escalaUseCases.criarEscalaAutomatica(request));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

    @PostMapping("/exportar-xlsx")
    public ResponseEntity<byte[]> exportarEscalaXLSX(@RequestBody List<ServicoOperacional> servicos) {
        try {
            var escalaXLSX = escalaUseCases.exportarEscalaXLSX(servicos);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=escala.xlsx")
                    .contentType(MediaType
                            .parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(escalaXLSX);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

}
