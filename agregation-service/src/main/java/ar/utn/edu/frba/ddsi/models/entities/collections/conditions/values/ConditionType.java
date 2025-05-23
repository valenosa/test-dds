package ar.utn.edu.frba.ddsi.models.entities.collections.conditions.values;

import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.impl.CategoryCondition;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.impl.FromDateCondition;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.impl.TitleCondition;
import ar.utn.edu.frba.ddsi.models.entities.collections.conditions.impl.UntilDateCondition;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import java.time.LocalDate;

public enum ConditionType {

  CATEGORY{
    @Override
    public ICondition toCondition(Object conditionValue) {
      return new CategoryCondition((Category) conditionValue);
    }
  },

  TITLE{
    @Override
    public ICondition toCondition(Object conditionValue) {
      return new TitleCondition((String) conditionValue);
    }
  },

  FROM_DATE{
    @Override
    public ICondition toCondition(Object conditionValue) {
      return new FromDateCondition((LocalDate) conditionValue);
    }
  },

  TO_DATE{
    @Override
    public ICondition toCondition(Object conditionValue) {
      return new UntilDateCondition((LocalDate) conditionValue);
    }
  };

  public abstract ICondition toCondition(Object conditionValue);
}
