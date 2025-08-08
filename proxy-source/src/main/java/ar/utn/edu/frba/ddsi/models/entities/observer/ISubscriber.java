package ar.utn.edu.frba.ddsi.models.entities.observer;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.source.Source;

import java.util.List;

public interface ISubscriber {
  void notifyEvents(List<EventOutputDTO> events);

  void notifySource(Source source);
}
