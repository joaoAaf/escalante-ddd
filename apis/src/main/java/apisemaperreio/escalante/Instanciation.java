package apisemaperreio.escalante;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import apisemaperreio.escalante.escalante.utils.adapters.ImportadorMilitaresXLSXAdapter;

@Configuration
public class Instanciation implements CommandLineRunner {

    @Autowired
    private ImportadorMilitaresXLSXAdapter importadorMilitaresXLSXAdapter;

    @Override
    public void run(String... args) throws Exception {

        File file = new File("../private/Planilha Importação Militares.xlsx");

        InputStream inputStream = new FileInputStream(file);

        MultipartFile multipartFile = new MockMultipartFile(
                "file", file.getName(), 
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                inputStream);

        var militares = importadorMilitaresXLSXAdapter.importarMilitaresXLSX(multipartFile);

        militares.forEach(System.out::println);

    }

}
