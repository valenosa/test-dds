package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.time.LocalDateTime;
import java.util.List;


public interface IEventRepository {
  void save(Event event);

  List<Event> findAll();

  List<Event> findByDeleted(boolean deleted);

  //Not eliminated, accepted & afterDate
  List<Event> findAfterDate(LocalDateTime date);

  //Not eliminated & accepted
  List<Event> findByAccepted();

  List<Event> findByPending();

  Event findById(Long eventId);
}
