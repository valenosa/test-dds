package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public abstract class Condicion {

  public abstract boolean cumple(Hecho hecho);
}
