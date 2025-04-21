package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public interface FiltroHecho {

  boolean cumple(Hecho hecho);
}
