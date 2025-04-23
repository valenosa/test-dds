package ar.edu.utn.frba.dds.domain.entities;

import lombok.Getter;

@Getter
public class Etiqueta {
  private final String nombre;

  public Etiqueta(String nombre) {
    this.nombre = nombre;
  }

}
