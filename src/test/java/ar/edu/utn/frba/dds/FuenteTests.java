package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.BaseDeDatos;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.fuente.CreadorFuente;
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

    //Validamos que los hechos se importen correctamente
    Assertions.assertEquals(5 , hechosImportados.size());

    //Validamos que se subieron a la "BD" (Este test no se si tiene mucho sentido xq se va a romper una vez creada la BD real)
    Assertions.assertEquals(5 , BaseDeDatos.hechos.size());

    //TODO: Buscar una mejor forma de validar que realmente los hechos se crearon de manera correcta (Validando la informacion interna)
  }
}
