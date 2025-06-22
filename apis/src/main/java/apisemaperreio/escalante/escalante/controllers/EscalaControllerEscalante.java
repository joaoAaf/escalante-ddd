package apisemaperreio.escalante.escalante.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.usecases.EscalaUseCasesEscalante;

@RequestMapping("/escala")
@RestController
public class EscalaControllerEscalante {

    @Autowired
    private EscalaUseCasesEscalante escalaUseCases;

    @PostMapping
    public ResponseEntity<byte[]> criarEscala(@RequestBody DadosEscala dadosEscala) {
        try {
            var servicosEscala = escalaUseCases.criarEscalaAutomatica(dadosEscala);
            var escalaXLSX = escalaUseCases.exportarEscalaXLSX(servicosEscala);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=escala.xlsx")
                    .contentType(MediaType
                            .parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(escalaXLSX);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

}
