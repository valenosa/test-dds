package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.InvalidStateChangeException;
import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.SubmissionEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SubmissionEvaluationOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.services.ISubmissionReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionReviewService implements ISubmissionReviewService {

  @Autowired
  IEventRepository eventRepository;


  @Override
  public SubmissionEvaluationOutputDTO evaluate(SubmissionEvaluationDTO submissionEvaluation) {

    //TODO: Validar si el usuario puede realizar esta peticion

    Event event = eventRepository.findById(submissionEvaluation.getEventId());
    if (event == null) throw new NotFoundException("Event not found - ID: " + submissionEvaluation.getEventId());

    // Check if state is the different from event state.
    if (event.getState().equals(submissionEvaluation.getSubmissionState())) {
      throw new InvalidStateChangeException("El hecho ya tiene el estado " + submissionEvaluation.getSubmissionState());
    }
    event.setState(submissionEvaluation.getSubmissionState());
    event.setSuggestion(submissionEvaluation.getSuggestion());

    Event eventSaved = eventRepository.save(event);
    return SubmissionEvaluationOutputDTO.from(eventSaved);
  }
}
