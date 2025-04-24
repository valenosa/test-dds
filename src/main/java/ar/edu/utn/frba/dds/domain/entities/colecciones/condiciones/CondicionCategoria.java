package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public class CondicionCategoria extends Condicion {
  String categoria;

  public CondicionCategoria(String categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return this.categoria.equals(hecho.getCategoria());
  }

}
