package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.submission.SubmissionRequest;
import java.util.List;

public interface ISubmissionRepository {
  SubmissionRequest save(SubmissionRequest request);

  SubmissionRequest findById(Long id);

  List<SubmissionRequest> findPendingSubmissions();
}
