package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.BaseDeDatos;
import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.fuente.CreadorFuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.Fuente;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.SolicitudEliminacionDeHecho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SolicitudEliminacionDeHechoTest {

  Coleccion unaColeccion;
  Fuente unaFuente;
  SolicitudEliminacionDeHecho unaSolicitud;
  Hecho unHecho;

  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    unaFuente = CreadorFuente.fuenteEstatica(pathCSV);
    unaColeccion = new Coleccion("Colección prueba", "Esto es una prueba", unaFuente);

    String justificacionValida = "A".repeat(500);

    BaseDeDatos db = BaseDeDatos.getInstance();

    unHecho = db.obtenerHecho("Caída de aeronave impacta en Olavarría");
    unaSolicitud = new SolicitudEliminacionDeHecho(unHecho, justificacionValida);
  }

  @Test
  @DisplayName("Al rechazar la solicitud de eliminación, el hecho aún puede ser agregado a una colección")
  public void testRejectRequest() {
    unaSolicitud.rechazar();

    //Se valida que el hecho fue marcadocomo eliminado
    Assertions.assertFalse(unHecho.isEliminado());
    //Se valida que no se agrega a una coleccion
    Assertions.assertTrue(unaColeccion.getHechosPertenecientes().contains(unHecho));
  }

  @Test
  @DisplayName("Al aceptar la solicitud de eliminación, el hecho NO puede ser agregado a una colección")
  public void testAceptRequest() {
    unaSolicitud.aceptar();

    //Se valida que el hecho NO fue marcado como eliminado
    Assertions.assertTrue(unHecho.isEliminado());
    //Se valida que no se agrega a una coleccion
    Assertions.assertFalse(unaColeccion.getHechosPertenecientes().contains(unHecho));
  }

  @Test
  @DisplayName("No es posible crear una solicitud de eliminación con una justificación invalida")
  public void testRequestTieneMenosDe500Caracteres() {
    String justificacionInvalida = "Justificación inválida";
    Assertions.assertThrows(IllegalArgumentException.class, () -> new SolicitudEliminacionDeHecho(unHecho, justificacionInvalida));


  }

  @Test
  @DisplayName("Un hecho debe poder recibir múltiples solicitudes en simultáneo")
  public void testHechoMultipleSolicitudes() {
    //TODO: Consultar que sucede con las solicitudes restantes en el caso de que se acepte una de ellas
  }
}
