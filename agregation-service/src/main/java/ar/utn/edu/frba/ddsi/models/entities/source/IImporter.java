package ar.utn.edu.frba.ddsi.models.entities.source;


import ar.utn.edu.frba.ddsi.models.entities.event.Event;

import java.util.Set;

public interface IImporter {
  Set<Event> importEvents(String path);
}