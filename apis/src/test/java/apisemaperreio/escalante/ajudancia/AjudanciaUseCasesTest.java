package apisemaperreio.escalante.ajudancia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import apisemaperreio.escalante.ajudancia.dtos.requestdtos.EmailRequest;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.EnderecoRequest;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.MilitarRequest;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.NomeRequest;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.PatenteRequest;
import apisemaperreio.escalante.ajudancia.dtos.requestdtos.TelefoneRequest;
import apisemaperreio.escalante.ajudancia.usecases.MilitarUseCasesAjudancia;
import apisemaperreio.escalante.ajudancia.utils.MilitarMapperAjudancia;

@SpringBootTest
@ActiveProfiles("test1")
class AjudanciaUseCasesTest {

	@Autowired
	private MilitarMapperAjudancia militarMapper;
	@Autowired
	private MilitarUseCasesAjudancia militarUseCases;

	@Test
	public void cadastrarMilitarTest_returnMilitar() {

		var nome = new NomeRequest("Fulano de Tal", "Fulano");
		var telefone = new TelefoneRequest("88", "99999999", null, null);
		var email = new EmailRequest("fulano@gmail.com", null);
		var endereco = new EnderecoRequest(null, null, null, null, null, "Limoeiro", "CE");
		var patente = new PatenteRequest("SGT", 5);

		MilitarRequest militarRequest = new MilitarRequest(
				"34567890",
				"99999999999",
				nome,
				LocalDate.parse("1991-06-24"),
				"M",
				telefone,
				email,
				endereco,
				patente,
				1000,
				4,
				true,
				false);

		var militarEsperado = militarMapper.toMilitar(militarRequest);
		militarEsperado.setFolgaEspecial(
				militarEsperado.definirFolgaEspecial(militarEsperado.getFolgaEspecial(),
						militarEsperado.getPatente().getFolgaEspecial()));
		
		var militarRetornado = militarUseCases.cadastrarMilitar(militarRequest);

		assertEquals(militarEsperado, militarRetornado);

	}

}
