package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import java.util.List;

public interface IEventService {
  public List<EventOutputDTO> getEvents(SubmissionState state);
  public EventOutputDTO getEventById(Long id);
  public void save(EventCreationDTO dto);
  public void update(EventUpdateDTO dto);
}