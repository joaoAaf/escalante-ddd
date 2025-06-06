package apisemaperreio.escalante.sharedcore.ajudancia_escalante;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import apisemaperreio.escalante.sharedcore.ajudancia_escalante.usecases.MilitarAjudanciaEscalanteUseCases;

@SpringBootTest
@ActiveProfiles("test2")
class AjudanciaEscalanteUseCasesTest {

	@Autowired
	private MilitarAjudanciaEscalanteUseCases militarEscalanteUseCases;

	@Test
	public void listarMilitaresEscalaveisTest_return23AndTrue() {

		final int QUANTIDADE_MILITARES_ESCALAVEIS = 23;
		final String MATRICULA_MILITAR_AUSENTE = "REG234";
		final LocalDate DATA_INICIO_ESCALA = LocalDate.parse("2024-09-01");
		final LocalDate DATA_FIM_ESCALA = LocalDate.parse("2024-09-30");

		var militaresEscalaveis = militarEscalanteUseCases.listarMilitaresEscalaveis(DATA_INICIO_ESCALA, DATA_FIM_ESCALA);
		assertEquals(QUANTIDADE_MILITARES_ESCALAVEIS, militaresEscalaveis.size());

		var militarAusente = militaresEscalaveis.stream()
				.filter(militar -> militar.matricula().equals(MATRICULA_MILITAR_AUSENTE)).findFirst();
		
		assertTrue(militarAusente.isEmpty());
		
	}

}
