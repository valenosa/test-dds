package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;

public interface ISourceService {
  void create(SourceInputDTO sourceInputDTO);
  List<Event> findEventsBySource(Long id);
}