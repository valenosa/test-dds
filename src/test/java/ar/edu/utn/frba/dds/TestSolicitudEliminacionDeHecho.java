package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import ar.edu.utn.frba.dds.domain.entities.colecciones.Coleccion;
import ar.edu.utn.frba.dds.domain.entities.excepciones.HechosConSolicitudesPendientesException;
import ar.edu.utn.frba.dds.domain.entities.importador.stretegies.CSVImportStrategy;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.EstadoSolicitud;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.SolicitudEliminacionDeHecho;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestSolicitudEliminacionDeHecho {

    SolicitudEliminacionDeHecho unaSolicitud;
    Hecho unHecho;
    Coleccion unaColeccion;

    @BeforeEach
    public void setUp() {
        String justificacion = "La tecnología ha transformado nuestras vidas de maneras profundas y en muchos casos inesperadas. Hoy, la conectividad instantánea, la inteligencia artificial y las innovaciones en el campo de la salud están cambiando la forma en que trabajamos, nos comunicamos y nos cuidamos. A través de la automatización, por ejemplo, se han logrado avances muy significativos en la productividad, pero también ha generado preocupaciones sobre el futuro del empleo y la ética en la toma de decisiones automatizada...";
        unHecho = new Hecho("Incendio en la torre Eiffel", "El dibu atajo 2 penales y los mandó a dormir", "Incendios patrióticos", 48.8584, 2.2945, LocalDate.now(), LocalDate.now());
        unaSolicitud = new SolicitudEliminacionDeHecho(unHecho, justificacion);
        unaColeccion = new Coleccion("Incendios en Francia", "Incendios en Francia");
    }

    //Rechazar esta solicitud un día después de su creación. Dado que fue rechazada, el hecho puede ser agregado a cualquier colección.
    @Test
    public void testRejectRequest() {
        unaSolicitud.rechazar();

        assertEquals(EstadoSolicitud.RECHAZADA, unaSolicitud.getEstado());

        unaColeccion.addHecho(unHecho);
        assertTrue(unaColeccion.getHechos().contains(unHecho));
    }

    @Test
    public void testAceptRequest() {
        unaSolicitud.aceptar();

        assertEquals(EstadoSolicitud.ACEPTADA, unaSolicitud.getEstado());

        assertThrows(HechosConSolicitudesPendientesException.class, () -> {
            unaColeccion.addHecho(unHecho);
        });
    }

    @Test
    public void testHechoHasMoreThan2Requests() {
        String justificacion1 = "Justificación válida 1 con más de 500 caracteres...".repeat(10);
        String justificacion2 = "Justificación válida 2 con más de 500 caracteres...".repeat(10);
        String justificacion3 = "Justificación válida 3 con más de 500 caracteres...".repeat(10);

        SolicitudEliminacionDeHecho solicitud1 = new SolicitudEliminacionDeHecho(unHecho, justificacion1);
        SolicitudEliminacionDeHecho solicitud2 = new SolicitudEliminacionDeHecho(unHecho, justificacion2);
        SolicitudEliminacionDeHecho solicitud3 = new SolicitudEliminacionDeHecho(unHecho, justificacion3);

        assertEquals(4, unHecho.getSolicitudesEliminacion().size());
        assertTrue(unHecho.getSolicitudesEliminacion().size() > 2);
    }

    @Test
    public void testRequestHasLessThan500Chars() {
        String justificacion = "Justificación inválida con menos de 500 caracteres.";
        assertThrows(IllegalArgumentException.class, () -> {
            new SolicitudEliminacionDeHecho(unHecho, justificacion);
        });
    }

    // No sabemos si al tener una solicitud pendiente, el hecho puede ser agregado a la colección o no.
    // @Test
    // public void testPendingRequest() {
    //     assertEquals(EstadoSolicitud.PENDIENTE, unaSolicitud.getEstado());
    //     unaColeccion.addHecho(unHecho);

    //     assertFalse(unaColeccion.getHechos().contains(unHecho));
    // }

}
