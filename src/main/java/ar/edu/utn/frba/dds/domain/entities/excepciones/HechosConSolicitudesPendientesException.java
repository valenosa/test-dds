package ar.edu.utn.frba.dds.domain.entities.excepciones;

public class HechosConSolicitudesPendientesException extends RuntimeException {
    public HechosConSolicitudesPendientesException() {
        super("No se puede agregar el hecho porque está eliminado o tiene solicitudes de eliminación pendientes.");
    }
}

