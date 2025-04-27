package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.fact.Category;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;

public class CategoryCondition extends Condition {
  Category category;

  public CategoryCondition(Category category) {
    this.category = category;
  }

  @Override
  public boolean isSatisfiedBy(Fact fact) {
    String conditionCategory = category.getName();
    String factCategory = fact.getCategory().getName();

    return conditionCategory.equals(factCategory);
  }

  // TODO: Se compara la cotegoria a partir del String, es correcto?

}
