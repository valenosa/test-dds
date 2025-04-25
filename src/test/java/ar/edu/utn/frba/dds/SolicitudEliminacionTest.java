package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.fuente.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.SolicitudEliminacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class SolicitudEliminacionTest {

  Coleccion unaColeccion;
  Fuente unaFuente;
  Hecho unHecho;
  String nombreUsuario;
  SolicitudEliminacion unaSolicitud;

  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_3.csv";
    unaFuente = new Fuente(pathCSV);

    unaColeccion = new Coleccion("Colección prueba", "Esto es una prueba", unaFuente);
    unaColeccion.calculateHechos();
    Set<Hecho> hechos = unaColeccion.getHechos();

    unHecho = hechos.iterator().next(); // Obtengo el primer hecho.

    String justificacionValida = "A".repeat(500);

    nombreUsuario = "Manolo";
    unaSolicitud = new SolicitudEliminacion(unHecho, justificacionValida, nombreUsuario);
  }

  @Test
  @DisplayName("Al rechazar la solicitud de eliminación, el hecho aún puede ser agregado a una colección")
  public void testRejectRequest() {
    unaSolicitud.rechazar("Jaime");

    //Se valida que el hecho no fue marcado como eliminado
    Assertions.assertFalse(unHecho.isEliminado());

    //Se valida que se agrega a una coleccion
    Assertions.assertTrue(unaColeccion.getHechos().contains(unHecho));
  }

  @Test
  @DisplayName("Al aceptar la solicitud de eliminación, el hecho NO puede ser agregado a una colección")
  public void testAceptRequest() {
    unaSolicitud.aceptar("Tomás");

    //Se valida que el hecho fue marcado como eliminado
    Assertions.assertTrue(unHecho.isEliminado());

    unaColeccion.calculateHechos();

    //Se valida que no se agrega a una coleccion
    Assertions.assertFalse(unaColeccion.getHechos().contains(unHecho));
  }

  @Test
  @DisplayName("No es posible crear una solicitud de eliminación con una justificación invalida")
  public void testRequestTieneMenosDe500Caracteres() {
    String justificacionInvalida = "Justificación inválida";
    Assertions.assertThrows(IllegalArgumentException.class, () -> new SolicitudEliminacion(unHecho, justificacionInvalida, nombreUsuario));
  }
}

//  @Test
//  @DisplayName("Un hecho debe poder recibir múltiples solicitudes en simultáneo")
//  public void testHechoMultipleSolicitudes() {
//    //TODO: Consultar que sucede con las solicitudes restantes en el caso de que se acepte una de ellas
//  }
//}
