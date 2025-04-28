package ar.edu.utn.frba.dds.domain.entities.report;

import lombok.Getter;

public class Category {
  @Getter private final String name;

  public Category(String name) {
    this.name = name;
  }
}
