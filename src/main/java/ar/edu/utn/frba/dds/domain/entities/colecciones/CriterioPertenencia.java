package ar.edu.utn.frba.dds.domain.entities.colecciones;

import ar.edu.utn.frba.dds.domain.entities.Hecho.Hecho;

public interface CriterioPertenencia {

  private boolean cumple(Hecho hecho) {
    return false;
  }
}
