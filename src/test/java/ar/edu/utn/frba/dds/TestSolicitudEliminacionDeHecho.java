package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.fuente.Fuente;
import ar.edu.utn.frba.dds.domain.entities.fuente.estrategias.EstrategiaDeImportacionEstatica;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.hecho.Origen;
import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.excepciones.HechoEliminadoException;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.EstadoSolicitud;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.SolicitudEliminacionDeHecho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestSolicitudEliminacionDeHecho {

  Coleccion unaColeccion;
  Fuente unaFuente;
  SolicitudEliminacionDeHecho unaSolicitud;

  @BeforeEach
  public void setUp() {
    String justificacion = "La tecnología ha transformado nuestras vidas de maneras profundas y en muchos casos inesperadas. Hoy, la conectividad instantánea, la inteligencia artificial y las innovaciones en el campo de la salud están cambiando la forma en que trabajamos, nos comunicamos y nos cuidamos. A través de la automatización, por ejemplo, se han logrado avances muy significativos en la productividad, pero también ha generado preocupaciones sobre el futuro del empleo y la ética en la toma de decisiones automatizada...";
    unaFuente = new Fuente(new EstrategiaDeImportacionEstatica("./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv"));
    unaColeccion = new Coleccion("Incendios en Francia", "Incendios en Francia", unaFuente);
    //TODO necesito podes crear una solicitud de eliminación que tenga persistencia
    unaSolicitud = new SolicitudEliminacionDeHecho(unHecho, justificacion);
  }

  //Rechazar esta solicitud un día después de su creación. Dado que fue rechazada, el hecho puede ser agregado a cualquier colección.
  @Test
  @DisplayName("Al rechazar la solicitud de eliminación, el hecho aún puede ser agregado a una colección")
  public void testRejectRequest() {
    unaSolicitud.rechazar();

    assertEquals(EstadoSolicitud.RECHAZADA, unaSolicitud.getEstado());

    assertTrue(unaColeccion.getHechos().contains(unHecho));
  }

  @Test
  @DisplayName("Al aceptar la solicitud de eliminación, el hecho NO puede ser agregado a una colección")
  public void testAceptRequest() {
    unaSolicitud.aceptar();

    assertEquals(EstadoSolicitud.ACEPTADA, unaSolicitud.getEstado());

    assertThrows(HechoEliminadoException.class, () -> {
      unaColeccion.addHecho(unHecho);
    });
  }

  @Test
  @DisplayName("No es posible crear una solicitud de eliminación con una justificación menor a 500 caracteres")
  public void testRequestTieneMenosDe500Caracteres() {
    String justificacion = "Justificación inválida con menos de 500 caracteres.";
    assertThrows(IllegalArgumentException.class, () -> {
      new SolicitudEliminacionDeHecho(unHecho, justificacion);
    });
  }

}
