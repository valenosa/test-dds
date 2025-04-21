package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public class FiltroXCategoria implements Filtro {
  String categoria;

  public FiltroXCategoria(String categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return this.categoria.equals(hecho.getCategoria());
  }

}
