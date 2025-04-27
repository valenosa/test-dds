package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Category;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;

public class ConditionCategory extends Condition {
  Category category;

  public ConditionCategory(Category category) {
    this.category = category;
  }

  @Override
  public boolean fulfills(Fact fact) {
    String categoryCondition = category.getNombre();
    String categoryFact = fact.getCategory().getNombre();

    return categoryCondition.equals(categoryFact);
  }

  // TODO: Se compara la cotegoria a partir del String, es correcto?

}
