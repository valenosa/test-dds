package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.event.Category;
import ar.edu.utn.frba.dds.domain.entities.event.Event;
import ar.edu.utn.frba.dds.domain.entities.event.Tag;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TagTest {
  @Test
  @DisplayName("Se agregan Etiquetas a un hecho")
  public void tagguear() {

    String title = "Caída de aeronave impacta en Venado Tuerto, Santa Fe";
    LocalDate dateEvent = LocalDate.of(2008, 8, 8);
    String description =  "Grave caída de aeronave ocurrió en las inmediaciones de Venado Tuerto, Santa Fe. El incidente destruyó viviendas y dejó a familias evacuadas. Autoridades nacionales se han puesto a disposición para brindar asistencia";

    Event event1 = new Event(title, description,new Category("Caída de aeronave"), -33.768051, -61.921032 , dateEvent, LocalDate.now(), null);

    Tag tag1 = new Tag("Olavarría");
    Tag tag2 = new Tag("Grave");

    event1.addTag(tag1);
    event1.addTag(tag2);


    Assertions.assertTrue(event1.tags.contains(tag1));
    Assertions.assertTrue(event1.tags.contains(tag2));
    Assertions.assertEquals(2, event1.tags.size());

  }
}

