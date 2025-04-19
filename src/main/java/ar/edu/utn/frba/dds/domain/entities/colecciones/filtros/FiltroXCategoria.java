package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.Hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.colecciones.FiltroHecho;

public class FiltroXCategoria implements FiltroHecho {

  String categoria;

  public FiltroXCategoria(String categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return this.categoria.equals(hecho.getCategoria());
  }

}
