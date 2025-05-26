package ar.utn.edu.frba.ddsi.models.repositories.impl;

import static org.junit.jupiter.api.Assertions.*;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class EventRepositoryTest {
  @Autowired
  private IEventRepository eventRepository;

  Event event0;
  Event event1;

  @BeforeEach
  public void setUp() {
    event0 = new Event(
        "Event 0",
        "Description 0",
        new Category("Category 0"),
        0.0,
        0.0,
        LocalDateTime.of(2023, 7, 27, 18, 15),
        Origin.STATIC,
        0L
    );

    event1 = new Event(
        "Event 1",
        "Description 1",
        new Category("Category 1"),
        0.0,
        0.0,
        LocalDateTime.of(2022, 12, 12, 9, 58),
        Origin.STATIC,
        1L
    );
    eventRepository.save(event0);
    eventRepository.save(event1);
  }

  @Test
  @DisplayName("Se obtienen hechos no eliminados")
  public void testFindEvents() {

    var result = eventRepository.findByDeleted(false);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(event0, result.get(0));
    assertEquals(event1, result.get(1));
  }

  @Test
  @DisplayName("Se obtienen hechos no actualizados")
  public void testFindAfterDate() {

    LocalDateTime lastUpdate = LocalDateTime.now().minusNanos(1_000_000);

    Event event2 = new Event(
        "Event 2",
        "Description 2",
        new Category("Category 2"),
        0.0,
        0.0,
        LocalDateTime.of(2021, 11, 12, 9, 58),
        Origin.STATIC,
        2L
    );

    eventRepository.save(event2);

    var result = eventRepository.findAfterDate(lastUpdate);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(event2, result.get(0));
  }

  @Test
  @DisplayName("Se elimina un hecho")
  public void testDeleteEvent() {
    var event = eventRepository.findById(1L);
    event.markAsDeleted();
    eventRepository.save(event);

    var events = eventRepository.findByDeleted(false);

    assertNotNull(events);
    assertEquals(1, events.size());
    assertEquals(event1, events.get(0));
  }

}