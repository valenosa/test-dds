package ar.edu.utn.frba.dds.domain.entities.hecho;

import lombok.Getter;

public class Categoria {
  @Getter private final String nombre;

  public Categoria(String nombre) {
    this.nombre = nombre;
  }
}
