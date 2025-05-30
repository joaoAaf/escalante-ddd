package apisemaperreio.escalante.ajudancia.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.EmailRequest;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.EnderecoRequest;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.NomeRequest;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.PatenteRequest;
import apisemaperreio.escalante.ajudancia.services.dtos.requestDtos.TelefoneRequest;
import apisemaperreio.escalante.ajudancia.services.useCases.MilitarUseCases;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final MilitarUseCases militarUseCases;

    public Instantiation(MilitarUseCases militarUseCases) {
        this.militarUseCases = militarUseCases;
    }

    @Override
    public void run(String... args) throws Exception {

        var nome = new NomeRequest("João Anderson", "Anderson");
        var telefone = new TelefoneRequest("88", "99999999", null, null);
        var email = new EmailRequest("joao@gmail.com", null);
        var endereco = new EnderecoRequest(null, null, null, null, null, "Limoeiro", "CE");
        var patente = new PatenteRequest("SGT", 5);



        MilitarRequest militarRequest = new MilitarRequest(
            "34567890",
            "00000000000",
            nome,
            LocalDate.parse("1991-06-24"),
            "M",
            telefone,
            email,
            endereco,
            patente,
            10,
            4,
            true,
            false);
        
        var militar = militarUseCases.cadastrarMilitar(militarRequest);

        System.out.println("Militar: " + militar);

    }

}
