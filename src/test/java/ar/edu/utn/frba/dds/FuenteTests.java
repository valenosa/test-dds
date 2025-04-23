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
  @DisplayName("CSV - Los hechos se importan")
  public void importarCSV() {

    String CSVPath = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    Fuente fuenteCSV = CreadorFuente.fuenteEstatica(CSVPath);

    Set<Hecho> hechosImportados = fuenteCSV.importHechos();

    //Validamos que los hechos se importen correctamente
    Assertions.assertEquals(5 , hechosImportados.size());
  }

  @Test
  @DisplayName("CSV - Los hechos se suben a la DB")
  public void subirCSV() {

    String CSVPath = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    Fuente fuenteCSV = CreadorFuente.fuenteEstatica(CSVPath);

    Set<Hecho> hechosImportados = fuenteCSV.importHechos();

    BaseDeDatos db = BaseDeDatos.getInstance();

    db.subirHechos(hechosImportados);

    // 🔍 Verificamos que todos los hechos importados estén en la BD con el mismo contenido
    Set<Hecho> hechosEnDB = BaseDeDatos.getInstance().obtenerHechos();

    Assertions.assertTrue(
        hechosEnDB.containsAll(hechosImportados),
        "No todos los hechos importados están presentes en la base de datos"
    );

  }


}
