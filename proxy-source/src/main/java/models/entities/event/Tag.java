package models.entities.event;

import lombok.Getter;

@Getter
public class Tag {
  private final String name;

  public Tag(String name) {
    this.name = name;
  }

}

