package ar.edu.utn.frba.dds.domain.entities.colecciones.condiciones;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

public class CondicionTitulo extends Condicion {

  String titulo;

  public CondicionTitulo(String titulo) {
    this.titulo = titulo;
  }

  @Override
  public boolean cumple(Hecho hecho) {
    return this.titulo.equals(hecho.getTitulo());
  }

}
