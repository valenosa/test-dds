package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.Hecho;

public interface CriterioPertenencia {

    boolean cumple(Hecho hecho);
}
