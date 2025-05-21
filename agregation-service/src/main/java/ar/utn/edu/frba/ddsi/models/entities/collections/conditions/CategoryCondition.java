package ar.utn.edu.frba.ddsi.models.entities.collections.conditions;

import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;

public class CategoryCondition extends Condition {
  Category category;

  public CategoryCondition(Category category) {
    this.category = category;
  }

  @Override
  public boolean isSatisfiedBy(Event event) {
    String conditionCategory = category.getName();
    String eventCategory = event.getCategory().getName();

    return conditionCategory.equals(eventCategory);
  }

  // TODO: Se compara la cotegoria a partir del String, es correcto?

}
