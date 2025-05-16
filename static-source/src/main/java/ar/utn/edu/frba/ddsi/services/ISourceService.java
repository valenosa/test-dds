package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import java.util.List;

public interface ISourceService {
  SourceOutputDTO create(SourceInputDTO sourceInputDTO);
  List<EventOutputDTO> findEventsBySource(Long id);
}