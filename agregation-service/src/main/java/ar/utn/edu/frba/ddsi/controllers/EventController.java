package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.services.impl.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventOutputDTO> getEvents(){
        return eventService.getEvents();
    }

}
