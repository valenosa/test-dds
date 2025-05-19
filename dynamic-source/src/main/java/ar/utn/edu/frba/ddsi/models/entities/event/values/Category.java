package ar.utn.edu.frba.ddsi.models.entities.event.values;

import lombok.Getter;

public class Category {
  @Getter private final String name;

  public Category(String name) {
    this.name = name;
  }
}
