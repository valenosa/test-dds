package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import ar.edu.utn.frba.dds.domain.entities.source.Source;
import ar.edu.utn.frba.dds.domain.entities.source.CSVImporter;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SourceTests {

  Source aSource;

  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    aSource = new Source(new CSVImporter(pathCSV));
  }


  @Test
  @DisplayName("CSV - Los hechos se importan")
  public void importarCSV() {
    Set<Event> events = aSource.getEvents();

    //Se valida que se generar la cantidad de hechos del sample_CSVtest_2
    Assertions.assertEquals(5, events.size());

    //Se valida que los datos fueron leidos y guardados de forma correcta
    Assertions.assertTrue(events.stream().anyMatch((h)->h.getTitle().equals("Caída de aeronave impacta en Olavarría")));
  }
}
