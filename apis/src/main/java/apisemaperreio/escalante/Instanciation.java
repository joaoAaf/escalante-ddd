package apisemaperreio.escalante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import apisemaperreio.escalante.escalante.domain.Escala;
import apisemaperreio.escalante.escalante.domain.ServicoOperacional;
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

        escala.getMilitaresEscalados().sort(Comparator.comparing(ServicoOperacional::getDataServico));

        escala.getMilitaresEscalados().forEach(System.out::println);

        for (var militar : militares) {
            var dataServicos = escala.getMilitaresEscalados().stream()
                    .filter(s -> {
                        var matriculaMilitar = Optional.ofNullable(s.getMatriculaMilitar());
                        if (matriculaMilitar.isEmpty())
                            return false;
                        return s.getMatriculaMilitar().equals(militar.getMatricula());
                    })
                    .map(ServicoOperacional::getDataServico).collect(Collectors.toList());
            System.out.println(militar);
            System.out.println("Data dos serviços: " + dataServicos);
            var folgas = new ArrayList<Integer>();
            for (var i = 1; i < dataServicos.size() - 1; i += 2) {
                if (dataServicos.size() > 2) {
                    var folga = dataServicos.get(i + 1).compareTo(dataServicos.get(i)) - 1;
                    folgas.add(folga);
                }
            }

            System.out.println("Folgas: " + folgas);
        }

        // Arrays.stream(Funcao.values()).forEach(System.out::println);

    }

}
