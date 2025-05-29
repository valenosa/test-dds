package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.List;


public interface IEventRepository {
  Event save(Event event);

  List<Event> findAll();

  List<Event> findByDeleted(boolean deleted);

  //Not eliminated, accepted & afterDate
  List<Event> findAfterDate(LocalDateTime date);

  //Not eliminated & accepted
  List<Event> findByAccepted();

  Event findById(Long eventId);

  Event delete(Event event);
}
