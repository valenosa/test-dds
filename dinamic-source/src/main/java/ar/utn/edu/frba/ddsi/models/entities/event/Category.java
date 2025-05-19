package ar.utn.edu.frba.ddsi.models.entities.event;

import lombok.Getter;

public class Category {
  @Getter private final String name;

  public Category(String name) {
    this.name = name;
  }
}
