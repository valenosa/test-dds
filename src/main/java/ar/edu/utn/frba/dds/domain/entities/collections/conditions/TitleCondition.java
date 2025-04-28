package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.event.Event;

public class TitleCondition extends Condition {

  String title;

  public TitleCondition(String title) {
    this.title = title;
  }

  public boolean isSatisfiedBy(Event event) {
    return this.title.equals(event.getTitle());
  }

}
