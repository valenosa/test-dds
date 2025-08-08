package ar.utn.edu.frba.ddsi.models.entities.source;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;

public interface ISourceClientAdapter {

  Long getId();

  void subscribe(String callbackUrl);

  List<Source> fetchSources();

  List<Event> fetchEventsBySource(Source source);

  void deleteEvent(Event event);

}