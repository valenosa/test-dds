package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequest;
import ar.utn.edu.frba.ddsi.models.repositories.IDeletionRequestRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class DeletionRequestRepository implements IDeletionRequestRepository {
  Map<Long, DeletionRequest> deletionRequests = new HashMap<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  @Override
  public void save(DeletionRequest deletionRequest) {
    if(deletionRequest.getId() == null){
      Long id = idGenerator.getAndIncrement();
      deletionRequest.setId(id);
      deletionRequests.put(id, deletionRequest);
    } else {
      deletionRequests.put(deletionRequest.getId(), deletionRequest);
    }
  }

  @Override
  public DeletionRequest getById(long id){
    return deletionRequests.get(id);
  }
}
