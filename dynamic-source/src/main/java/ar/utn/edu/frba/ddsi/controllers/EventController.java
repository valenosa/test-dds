package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.services.IEventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/event")
public class EventController {

  @Autowired
  private IEventService eventService;

  @PostMapping
  public void submitEvent(@RequestBody EventCreationDTO eventDto) {
    eventService.save(eventDto);
  }

  @PutMapping
  public void updateEvent(@RequestBody EventUpdateDTO eventDto) {
    eventService.update(eventDto);
  }

  @GetMapping()
  public List<EventOutputDTO> getEvents() {
    return eventService.getEvents();
  }
}
