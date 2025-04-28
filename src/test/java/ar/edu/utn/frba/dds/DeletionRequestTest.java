package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.collections.Collection;
import ar.edu.utn.frba.dds.domain.entities.report.Report;
import ar.edu.utn.frba.dds.domain.entities.source.Source;
import ar.edu.utn.frba.dds.domain.entities.source.CSVImporter;
import ar.edu.utn.frba.dds.domain.entities.requests.DeletionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class DeletionRequestTest {

  Collection aCollection;
  Source aSource;
  Report aReport;
  String userName;
  DeletionRequest aApplication;

  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_3.csv";
    aSource = new Source(new CSVImporter(pathCSV));

    aCollection = new Collection("Colección prueba", "Esto es una prueba", aSource);
    aCollection.fetchReports();
    Set<Report> reports = aCollection.getReports();

    aReport = reports.iterator().next(); // Obtengo el primer hecho.

    String validJustification = "A".repeat(500);

    userName = "Manolo";
    aApplication = new DeletionRequest(aReport, validJustification, userName);
  }

  @Test
  @DisplayName("Al rechazar la solicitud de eliminación, el hecho aún puede ser agregado a una colección")
  public void testRejectRequest() {
    aApplication.reject("Jaime");

    //Se valida que el hecho no fue marcado como eliminado
    Assertions.assertFalse(aReport.isDeleted());

    //Se valida que se agrega a una coleccion
    Assertions.assertTrue(aCollection.getReports().contains(aReport));
  }

  @Test
  @DisplayName("Al aceptar la solicitud de eliminación, el hecho NO puede ser agregado a una colección")
  public void testAcceptRequest() {
    aApplication.accept("Tomás");

    //Se valida que el hecho fue marcado como eliminado
    Assertions.assertTrue(aReport.isDeleted());

    aCollection.fetchReports();

    //Se valida que no se agrega a una coleccion
    Assertions.assertFalse(aCollection.getReports().contains(aReport));
  }

  @Test
  @DisplayName("No es posible crear una solicitud de eliminación con una justificación invalida")
  public void testRequestHasless500characters() {
    String invalidJustification = "Justificación inválida";
    Assertions.assertThrows(IllegalArgumentException.class, () -> new DeletionRequest(aReport, invalidJustification, userName));
  }
}

//  @Test
//  @DisplayName("Un hecho debe poder recibir múltiples solicitudes en simultáneo")
//  public void testHechoMultipleSolicitudes() {
//    //TODO: Consultar que sucede con las solicitudes restantes en el caso de que se acepte una de ellas
//  }
//}
