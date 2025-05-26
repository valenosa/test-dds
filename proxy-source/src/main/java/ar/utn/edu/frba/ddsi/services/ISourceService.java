package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface ISourceService {
  List<EventOutputDTO> getEvents(LocalDateTime lastUpdate);
}
