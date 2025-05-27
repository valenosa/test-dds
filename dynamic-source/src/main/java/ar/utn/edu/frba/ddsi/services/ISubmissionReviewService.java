package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.SubmissionEvaluationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.SubmissionEvaluationOutputDTO;

public interface ISubmissionReviewService {
  SubmissionEvaluationOutputDTO evaluate(SubmissionEvaluationDTO submissionEvaluation);
}
