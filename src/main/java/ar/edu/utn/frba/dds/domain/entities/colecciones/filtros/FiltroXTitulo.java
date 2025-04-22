package ar.edu.utn.frba.dds.domain.entities.colecciones.filtros;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public class FiltroXTitulo implements Filtro {

  String titulo;

  public FiltroXTitulo(String titulo) {
    this.titulo = titulo;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return this.titulo.equals(hecho.getTitulo());
  }

}
