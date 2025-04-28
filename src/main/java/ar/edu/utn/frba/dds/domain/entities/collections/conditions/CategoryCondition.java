package ar.edu.utn.frba.dds.domain.entities.collections.conditions;

import ar.edu.utn.frba.dds.domain.entities.report.Category;
import ar.edu.utn.frba.dds.domain.entities.report.Report;

public class CategoryCondition extends Condition {
  Category category;

  public CategoryCondition(Category category) {
    this.category = category;
  }

  @Override
  public boolean isSatisfiedBy(Report report) {
    String conditionCategory = category.getName();
    String reportCategory = report.getCategory().getName();

    return conditionCategory.equals(reportCategory);
  }

  // TODO: Se compara la cotegoria a partir del String, es correcto?

}
