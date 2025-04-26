package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.fuente.Fuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.ImportadorCSV;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuenteTests {

  Fuente unaFuente;

  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    unaFuente = new Fuente(new ImportadorCSV(pathCSV));
  }


  @Test
  @DisplayName("CSV - Los hechos se importan")
  public void importarCSV() {
    Set<Hecho> hechos = unaFuente.getHechos();

    //Se valida que se generar la cantidad de hechos del sample_CSVtest_2
    Assertions.assertEquals(5, hechos.size());

    //Se valida que los datos fueron leidos y guardados de forma correcta
    Assertions.assertTrue(hechos.stream().anyMatch((h)->h.getTitulo().equals("Caída de aeronave impacta en Olavarría")));
  }
}
