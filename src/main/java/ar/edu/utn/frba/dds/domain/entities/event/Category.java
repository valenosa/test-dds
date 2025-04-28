package ar.edu.utn.frba.dds.domain.entities.event;

import lombok.Getter;

public class Category {
  @Getter private final String name;

  public Category(String name) {
    this.name = name;
  }
}
