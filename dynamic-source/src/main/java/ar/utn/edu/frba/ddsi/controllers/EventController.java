package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.EventCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.input.EventUpdateDTO;
import ar.utn.edu.frba.ddsi.services.impl.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/event")
public class EventController {

  @Autowired
  private EventService eventService;

  @PostMapping
  public void createEvent(@RequestBody EventCreationDTO eventDto) {
    eventService.save(eventDto);
  }

  @PutMapping
  public void updateEvent(@RequestBody EventUpdateDTO eventDto) {
    eventService.update(eventDto);
  }

}
