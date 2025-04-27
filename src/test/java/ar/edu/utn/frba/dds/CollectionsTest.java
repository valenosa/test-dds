package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.collections.Collections;
import ar.edu.utn.frba.dds.domain.entities.collections.conditions.ConditionCategory;
import ar.edu.utn.frba.dds.domain.entities.collections.conditions.ConditionBetweenDate;
import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import ar.edu.utn.frba.dds.domain.entities.source.Source;
import ar.edu.utn.frba.dds.domain.entities.source.ImporterCSV;
import ar.edu.utn.frba.dds.domain.entities.fact.Category;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CollectionsTest {

  Collections aCollections;
  Source aSource;


  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    aSource = new Source(new ImporterCSV(pathCSV));
    aCollections = new Collections("Colección prueba", "Esto es una prueba", aSource);
  }

  @Test
  @DisplayName("Se pueden obtener hechos a partir de una colección")
  void testValidacionDeObtencionDeHechos() {
    Set<Fact> facts = aCollections.getFacts();

    Assertions.assertEquals(5, facts.size());
  }

  @Test
  @DisplayName("Se aplicar Criterios de pertenencia")
  void testJudgmentBelonging() {
    //Creo un set de hechos asociados
    Set<Fact> associatedFacts;

    //Agrego condicion entreFechas y recalculo
    aCollections.addCondition(new ConditionBetweenDate(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 1, 1)));

    //Ya tengo el los condiciones listos, me guardo los hechos de la colección
    associatedFacts = aCollections.getFacts();

    Assertions.assertEquals(3, associatedFacts.size());

    //Agrego condicion por categoria
    aCollections.addCondition(new ConditionCategory(new Category("Caída de aeronave")));
    associatedFacts = aCollections.getFacts();

    Assertions.assertEquals(2, associatedFacts.size());

  }

}