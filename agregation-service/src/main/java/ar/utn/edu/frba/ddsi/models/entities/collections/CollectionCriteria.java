package ar.utn.edu.frba.ddsi.models.entities.collections;

import ar.utn.edu.frba.ddsi.models.dtos.input.ConditionDTO;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.ICondition;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;

public class CollectionCriteria {

  List<ICondition> conditions;

  public static CollectionCriteria from(List<ConditionDTO> conditionsDto) {
    List<ICondition> conditions = conditionsDto.stream().map(c -> c.getConditionType().toCondition(c.getConditionValue())).toList();
    return new CollectionCriteria(conditions);
  }

  public CollectionCriteria(List<ICondition> conditions) {
    this.conditions = conditions;
  }

  public void addCondition(ICondition condition, ICondition... conditions) {
    this.conditions.add(condition);
    if (conditions != null) {
      this.conditions.addAll(List.of(conditions));
    }
  }

  public boolean isSatisfiedBy(Event event) {
    return conditions.stream().allMatch(condition -> condition.isSatisfiedBy(event));
  }
}
