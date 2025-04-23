package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public class FiltroCategoria implements Filtro {
  String categoria;

  public FiltroCategoria(String categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return this.categoria.equals(hecho.getCategoria());
  }

}
