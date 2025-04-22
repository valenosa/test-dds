package ar.edu.utn.frba.dds.domain.entities;

import lombok.Getter;

@Getter
public class Etiqueta {
  private String nombre;

  public Etiqueta(String nombre) {
    this.nombre = nombre;
  }

}
