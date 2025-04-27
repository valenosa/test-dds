package ar.edu.utn.frba.dds.domain.entities.collections;

import ar.edu.utn.frba.dds.domain.entities.collections.conditions.Condition;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.util.ArrayList;
import java.util.List;

public class BelongingCriteria {

  List<Condition> conditions;

  public BelongingCriteria() {
    conditions = new ArrayList<>();
  }

  public void addCondition(Condition condition, Condition... conditions) {
    this.conditions.add(condition);
    if (conditions != null) {
      this.conditions.addAll(List.of(conditions));
    }
  }

  public boolean isSatisfiedBy(Fact fact) {
    return conditions.stream().allMatch(condition -> condition.isSatisfiedBy(fact));
  }
}
