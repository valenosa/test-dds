package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.exceptions.InvalidStateChangeException;
import ar.utn.edu.frba.ddsi.exceptions.NotFoundException;
import ar.utn.edu.frba.ddsi.models.dtos.input.SubmissionEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SubmissionEvaluationOutputDTO;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.submission.SubmissionRequest;
import ar.utn.edu.frba.ddsi.models.repositories.IEventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.ISubmissionRepository;
import ar.utn.edu.frba.ddsi.services.ISubmissionReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionReviewService implements ISubmissionReviewService {

  @Autowired
  ISubmissionRepository submissionRepository;

  @Autowired
  IEventRepository eventRepository;


  @Override
  public SubmissionEvaluationOutputDTO evaluate(SubmissionEvaluationDTO submissionEvaluation) {

    //TODO: Validar si el usuario puede realizar esta peticion

    SubmissionRequest submission = submissionRepository.findById(submissionEvaluation.getSubmissionId());
    if (submission == null)
      throw new NotFoundException("Submission not found - ID: " + submissionEvaluation.getSubmissionId());

    Event event = eventRepository.findById(submission.getEventId());
    if (event == null) throw new NotFoundException("Event not found - ID: " + submission.getEventId());

    // Check if state is different from event state.
    if (submission.getState().equals(submissionEvaluation.getSubmissionState())) {
      throw new InvalidStateChangeException("The event already has state: " + submissionEvaluation.getSubmissionState());
    }

    submission.setEvaluation(
        submissionEvaluation.getReviewer(),
        submissionEvaluation.getSubmissionState(),
        submissionEvaluation.getSuggestion()
    );

    if (submission.isAccepted()) {
      event.markAsAccepted();
    }

    SubmissionRequest submissionRequest = submissionRepository.save(submission);
    Event eventSaved = eventRepository.save(event);
    return SubmissionEvaluationOutputDTO.from(eventSaved, submissionRequest.getState());
  }

}
