package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.collections.Collection;
import ar.edu.utn.frba.dds.domain.entities.collections.conditions.CategoryCondition;
import ar.edu.utn.frba.dds.domain.entities.collections.conditions.BetweenDatesCondition;
import ar.edu.utn.frba.dds.domain.entities.event.Event;
import ar.edu.utn.frba.dds.domain.entities.source.Source;
import ar.edu.utn.frba.dds.domain.entities.source.CsvImporter;
import ar.edu.utn.frba.dds.domain.entities.event.Category;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CollectionTest {

  Collection aCollection;
  Source aSource;


  @BeforeEach
  public void setUp() {
    String pathCSV = "./src/test/java/ar/edu/utn/frba/dds/resources/CSV/sample_CSVtest_2.csv";
    aSource = new Source(new CsvImporter(pathCSV));
    aCollection = new Collection("Colección prueba", "Esto es una prueba", aSource);
  }

  @Test
  @DisplayName("Se pueden obtener hechos a partir de una colección")
  void testValidacionDeObtencionDeHechos() {
    Set<Event> events = aCollection.getEvents();

    Assertions.assertEquals(5, events.size());
  }

  @Test
  @DisplayName("Se aplicar Criterios de pertenencia")
  void testColllectionCriteria() {
    //Creo un set de hechos asociados
    Set<Event> associatedEvents;

    //Agrego condicion entreFechas y recalculo
    aCollection.addCondition(new BetweenDatesCondition(LocalDate.of(2000, 1, 1), LocalDate.of(2010, 1, 1)));

    //Ya tengo el los condiciones listos, me guardo los hechos de la colección
    associatedEvents = aCollection.getEvents();

    Assertions.assertEquals(3, associatedEvents.size());

    //Agrego condicion por categoria
    aCollection.addCondition(new CategoryCondition(new Category("Caída de aeronave")));
    associatedEvents = aCollection.getEvents();

    Assertions.assertEquals(2, associatedEvents.size());

  }

}