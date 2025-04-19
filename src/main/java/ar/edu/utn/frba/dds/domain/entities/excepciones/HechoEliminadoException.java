package ar.edu.utn.frba.dds.domain.entities.excepciones;

public class HechoEliminadoException extends RuntimeException {
    public HechoEliminadoException() {
        super("No se puede agregar el hecho porque está eliminado.");
    }
}

