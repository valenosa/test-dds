package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import org.springframework.stereotype.Repository;

@Repository
public interface ISourceClientRepository {
  void save(SourceClient sourceClient);
}
