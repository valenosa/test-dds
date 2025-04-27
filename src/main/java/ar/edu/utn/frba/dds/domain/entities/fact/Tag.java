package ar.edu.utn.frba.dds.domain.entities.fact;

import lombok.Getter;

@Getter
public class Tag {
  private final String name;

  public Tag(String name) {
    this.name = name;
  }

}
