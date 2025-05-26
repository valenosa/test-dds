package ar.utn.edu.frba.ddsi.controllers;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubmissionEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import ar.utn.edu.frba.ddsi.services.IEventService;
import ar.utn.edu.frba.ddsi.services.ISubmissionReviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("submissions")
public class SubmissionRequestController {

  @Autowired
  IEventService eventService;

  @Autowired
  ISubmissionReviewService submissionReviewService;

  @GetMapping("/events")
  public List<EventOutputDTO> getPendingEvents() {
    return eventService.getPendingEvents();
  }

  @PutMapping
  public void evaluateEvent(@RequestBody SubmissionEvaluationDTO eventDto) {
    submissionReviewService.evaluate(eventDto);
  }
}
