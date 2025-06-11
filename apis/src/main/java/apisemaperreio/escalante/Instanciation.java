package apisemaperreio.escalante;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import apisemaperreio.escalante.escalante.domain.Escala;
import apisemaperreio.escalante.escalante.domain.Funcao;
import apisemaperreio.escalante.escalante.usecases.MilitarUseCasesEscalante;

@Configuration
public class Instanciation implements CommandLineRunner {

    @Autowired
    private MilitarUseCasesEscalante militarUseCasesEscalante;

    @Override
    public void run(String... args) throws Exception {

        var dataInicio = LocalDate.parse("2024-09-01");
        var dataFim = LocalDate.parse("2024-09-30");
        
        var militares = militarUseCasesEscalante.listarMilitaresEscalaveis(dataInicio, dataFim);

        var escala = new Escala(dataInicio, dataFim, 2);

        escala.preencherEscala(militares);

        escala.getMilitaresEscalados().forEach(System.out::println);

        Arrays.stream(Funcao.values()).forEach(System.out::println);

    }

}
