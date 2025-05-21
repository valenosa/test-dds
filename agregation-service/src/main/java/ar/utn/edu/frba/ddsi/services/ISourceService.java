package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SourceOutputDTO;

import java.util.List;

public interface ISourceService {
  SourceOutputDTO create(SourceInputDTO sourceInputDTO);
  List<Long> getSources();
  List<EventOutputDTO> getEvents();
  List<EventOutputDTO> findEventsBySource(Long id);
}