package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import java.time.LocalDate;

public class UntilDateCondition extends Condition {

  LocalDate to;

  public UntilDateCondition(LocalDate to) {
    this.to = to;
  }

  @Override
  public boolean isSatisfiedBy(Event event) {
    return event.getEventDate().isBefore(to);
  }
}
