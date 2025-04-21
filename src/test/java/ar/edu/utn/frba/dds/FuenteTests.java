package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.fuente.CreadorFuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.FuenteEstatica;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuenteTests {

  @Test
  @DisplayName("CSV - Los hechos se importan correctamente")
  public void importarCSV() {

    String CSVPath = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    Fuente fuenteCSV = CreadorFuente.fuenteEstatica(CSVPath);

    Set<Hecho> hechosImportados = fuenteCSV.importHechos();



    Assertions.assertEquals(5 , hechosImportados.size() );
  }
}
