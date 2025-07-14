package apisemaperreio.escalante.escalante;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.utils.adapters.ImportadorMilitaresXLSXAdapter;

@SpringBootTest
class EscalanteTest {

	@Autowired
	private ImportadorMilitaresXLSXAdapter importadorMilitaresXLSXAdapter;

	@Test
	public void importarMilitaresXLSXTest_return25True7() throws Exception {

		final int QUANTIDADE_MILITARES_ESCALAVEIS = 25;
		final String MATRICULA_MILITAR_FOLGA_ESPECIAL = "REG835";
		final int FOLGA_ESPECIAL = 7;

		File file = new File("../samples/modelo_importação_militares.xlsx");

		InputStream inputStream = new FileInputStream(file);

		MultipartFile multipartFile = new MockMultipartFile(
				"file", file.getName(),
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				inputStream);

		var militaresEscalaveis = importadorMilitaresXLSXAdapter.importarMilitaresXLSX(multipartFile);

		assertEquals(QUANTIDADE_MILITARES_ESCALAVEIS, militaresEscalaveis.size());

		var militarFolgaEspecial = militaresEscalaveis.stream()
				.filter(militar -> militar.matricula().equals(MATRICULA_MILITAR_FOLGA_ESPECIAL)).findFirst();

		assertTrue(militarFolgaEspecial.isPresent());

		assertEquals(FOLGA_ESPECIAL, militarFolgaEspecial.get().folgaEspecial());

	}

}
