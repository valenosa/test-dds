package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.submission.SubmissionRequest;
import ar.utn.edu.frba.ddsi.models.repositories.ISubmissionRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class SubmissionRepository implements ISubmissionRepository {
  Map<Long, SubmissionRequest> submissions = new HashMap<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  @Override
  public SubmissionRequest save(SubmissionRequest submission) {
    if (submission.getId() == null) {
      Long id = idGenerator.getAndIncrement();
      submission.setId(id);
      submissions.put(id, submission);
    } else {
      submissions.put(submission.getId(), submission);
    }
    return submission;
  }

  @Override
  public SubmissionRequest findById(Long id) {
    return submissions.get(id);
  }

  @Override
  public List<SubmissionRequest> findPendingSubmissions() {
    return submissions.values().stream().filter(SubmissionRequest::isPending).toList();
  }
}
