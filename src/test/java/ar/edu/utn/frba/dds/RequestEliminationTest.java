package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.collections.Collections;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import ar.edu.utn.frba.dds.domain.entities.source.Source;
import ar.edu.utn.frba.dds.domain.entities.source.ImporterCSV;
import ar.edu.utn.frba.dds.domain.entities.requests.RequestElimination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class RequestEliminationTest {

  Collections aCollections;
  Source aSource;
  Fact aFact;
  String userName;
  RequestElimination aApplication;

  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_3.csv";
    aSource = new Source(new ImporterCSV(pathCSV));

    aCollections = new Collections("Colección prueba", "Esto es una prueba", aSource);
    aCollections.calculateFacts();
    Set<Fact> facts = aCollections.getFacts();

    aFact = facts.iterator().next(); // Obtengo el primer hecho.

    String validJustification = "A".repeat(500);

    userName = "Manolo";
    aApplication = new RequestElimination(aFact, validJustification, userName);
  }

  @Test
  @DisplayName("Al rechazar la solicitud de eliminación, el hecho aún puede ser agregado a una colección")
  public void testRejectRequest() {
    aApplication.reject("Jaime");

    //Se valida que el hecho no fue marcado como eliminado
    Assertions.assertFalse(aFact.isEliminated());

    //Se valida que se agrega a una coleccion
    Assertions.assertTrue(aCollections.getFacts().contains(aFact));
  }

  @Test
  @DisplayName("Al aceptar la solicitud de eliminación, el hecho NO puede ser agregado a una colección")
  public void testAcceptRequest() {
    aApplication.accept("Tomás");

    //Se valida que el hecho fue marcado como eliminado
    Assertions.assertTrue(aFact.isEliminated());

    aCollections.calculateFacts();

    //Se valida que no se agrega a una coleccion
    Assertions.assertFalse(aCollections.getFacts().contains(aFact));
  }

  @Test
  @DisplayName("No es posible crear una solicitud de eliminación con una justificación invalida")
  public void testRequestHasless500characters() {
    String invalidJustification = "Justificación inválida";
    Assertions.assertThrows(IllegalArgumentException.class, () -> new RequestElimination(aFact, invalidJustification, userName));
  }
}

//  @Test
//  @DisplayName("Un hecho debe poder recibir múltiples solicitudes en simultáneo")
//  public void testHechoMultipleSolicitudes() {
//    //TODO: Consultar que sucede con las solicitudes restantes en el caso de que se acepte una de ellas
//  }
//}
