package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.request.DeletionRequest;

public interface IDeletionRequestRepository {
  void save(DeletionRequest deletionRequest);
  DeletionRequest getById(long id);
}
