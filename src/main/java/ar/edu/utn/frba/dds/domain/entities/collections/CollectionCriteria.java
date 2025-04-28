package ar.edu.utn.frba.dds.domain.entities.collections;

import ar.edu.utn.frba.dds.domain.entities.collections.conditions.Condition;
import ar.edu.utn.frba.dds.domain.entities.report.Report;
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

  public boolean isSatisfiedBy(Report report) {
    return conditions.stream().allMatch(condition -> condition.isSatisfiedBy(report));
  }
}
