package ar.utn.edu.frba.ddsi.models.entities.collections.conditions;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;

public class TitleCondition extends Condition {

  String title;

  public TitleCondition(String title) {
    this.title = title;
  }

  public boolean isSatisfiedBy(Event event) {
    return this.title.equals(event.getTitle());
  }

}
