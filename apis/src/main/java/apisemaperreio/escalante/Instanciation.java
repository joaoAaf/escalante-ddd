package apisemaperreio.escalante;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import apisemaperreio.escalante.escalante.domain.DadosEscala;
import apisemaperreio.escalante.escalante.usecases.EscalaUseCasesEscalante;

@Configuration
public class Instanciation implements CommandLineRunner {

    @Autowired
    private EscalaUseCasesEscalante escalaUseCases;

    @Override
    public void run(String... args) throws Exception {

        var dataInicio = LocalDate.parse("2024-09-01");
        var dataFim = LocalDate.parse("2024-09-30");

        var dadosEscala = new DadosEscala(dataInicio, dataFim, 2);

        var escala = escalaUseCases.criarEscalaAutomatica(dadosEscala);

        escala.forEach(System.out::println);

        // for (var militar : militares) {
        //     var dataServicos = escala.getMilitaresEscalados().stream()
        //             .filter(s -> {
        //                 var matriculaMilitar = Optional.ofNullable(s.getMilitar().getMatricula());
        //                 if (matriculaMilitar.isEmpty())
        //                     return false;
        //                 return s.getMilitar().getMatricula().equals(militar.getMatricula());
        //             })
        //             .map(ServicoOperacional::getDataServico)
        //             .distinct().collect(Collectors.toList());
        //     System.out.println(militar);
        //     System.out.println("Data dos serviços: " + dataServicos);
        //     var folgas = new ArrayList<Integer>();
        //     for (var i = 1; i < dataServicos.size() - 1; i += 2) {
        //         if (dataServicos.size() > 2) {
        //             var folga = dataServicos.get(i + 1).compareTo(dataServicos.get(i)) - 1;
        //             folgas.add(folga);
        //         }
        //     }

        //     System.out.println("Folgas: " + folgas);
        // }

    }

}
