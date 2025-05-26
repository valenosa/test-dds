package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubmissionEvaluationDTO;

public interface ISubmissionReviewService {
  void evaluate(SubmissionEvaluationDTO submissionEvaluation);
}
