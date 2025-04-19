package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.Hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.importador.Importador;
import ar.edu.utn.frba.dds.domain.entities.importador.stretegies.CSVImportStrategy;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestImportador {

  @Test
  @DisplayName("CSV - Los hechos se importan correctamente")
  public void importarCSV() {

    String CSVPath = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    Importador importadorCSV = new Importador(new CSVImportStrategy(CSVPath));

    Set<Hecho> hechosImportados = importadorCSV.importHechos();



    Assertions.assertEquals(5 , hechosImportados.size() );
  }
}
