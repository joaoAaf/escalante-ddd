package apisemaperreio.escalante.escalante;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test2")
class EscalanteUseCasesTest {

	// @Autowired
	// private MilitarUseCasesEscalante militarEscalavelUseCases;

	// @Test
	// public void listarMilitaresEscalaveisTest_return23AndTrue() {

	// 	final int QUANTIDADE_MILITARES_ESCALAVEIS = 23;
	// 	final String MATRICULA_MILITAR_AUSENTE = "REG234";
	// 	final LocalDate DATA_INICIO_ESCALA = LocalDate.parse("2024-09-01");
	// 	final LocalDate DATA_FIM_ESCALA = LocalDate.parse("2024-09-30");

	// 	var militaresEscalaveis = militarEscalavelUseCases.listarMilitaresEscalaveis(DATA_INICIO_ESCALA, DATA_FIM_ESCALA);
	// 	assertEquals(QUANTIDADE_MILITARES_ESCALAVEIS, militaresEscalaveis.size());

	// 	var militarAusente = militaresEscalaveis.stream()
	// 			.filter(militar -> militar.getMatricula().equals(MATRICULA_MILITAR_AUSENTE)).findFirst();
		
	// 	assertTrue(militarAusente.isEmpty());
		
	// }

}
