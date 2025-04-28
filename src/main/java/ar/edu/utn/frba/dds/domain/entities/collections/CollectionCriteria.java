package ar.edu.utn.frba.dds.domain.entities.collections;

import ar.edu.utn.frba.dds.domain.entities.collections.conditions.Condition;
import ar.edu.utn.frba.dds.domain.entities.event.Event;
import java.util.ArrayList;
import java.util.List;

public class CollectionCriteria {

  List<Condition> conditions;

  public CollectionCriteria() {
    conditions = new ArrayList<>();
  }

  public void addCondition(Condition condition, Condition... conditions) {
    this.conditions.add(condition);
    if (conditions != null) {
      this.conditions.addAll(List.of(conditions));
    }
  }

  public boolean isSatisfiedBy(Event event) {
    return conditions.stream().allMatch(condition -> condition.isSatisfiedBy(event));
  }
}
