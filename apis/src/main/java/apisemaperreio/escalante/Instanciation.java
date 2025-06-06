package apisemaperreio.escalante;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import apisemaperreio.escalante.sharedcore.ajudancia_escalante.usecases.MilitarAjudanciaEscalanteUseCases;

@Configuration
public class Instanciation implements CommandLineRunner {

    private final MilitarAjudanciaEscalanteUseCases militarEscalanteUseCases;

    public Instanciation(MilitarAjudanciaEscalanteUseCases militarEscalanteUseCases) {
        this.militarEscalanteUseCases = militarEscalanteUseCases;
    }

    @Override
    public void run(String... args) throws Exception {

        var militaresEscalaveis = militarEscalanteUseCases.listarMilitaresEscalaveis(LocalDate.parse("2024-09-01"),
                LocalDate.parse("2024-09-30"));

        System.out.println(militaresEscalaveis);

        var militar = militaresEscalaveis.stream().filter(m -> m.matricula().equals("REG234")).findFirst();

        if (militar.isPresent()) {
            System.out.println("Militar encontrado: " + militar.get());
        } else {
            System.out.println("Militar não encontrado");
        }

    }

}
