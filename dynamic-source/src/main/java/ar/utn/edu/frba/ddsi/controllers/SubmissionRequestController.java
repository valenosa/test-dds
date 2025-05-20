package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubmissionEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.values.SubmissionState;
import ar.utn.edu.frba.ddsi.services.IEventService;
import ar.utn.edu.frba.ddsi.services.ISubmissionReviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("api/submission")
public class SubmissionRequestController {

  @Autowired
  IEventService eventService;

  @Autowired
  ISubmissionReviewService submissionReviewService;

  @GetMapping("/event")
  public List<EventOutputDTO> getPendingEvents() {
    return eventService.getEvents(SubmissionState.PENDING);
  }

  @PutMapping
  public void evaluateEvent(@RequestBody SubmissionEvaluationDTO eventDto) {
    submissionReviewService.evaluate(eventDto);
  }
}
