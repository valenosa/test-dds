package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISourceClientRepository {
  SourceClient save(SourceClient sourceClient);

  SourceClient findById(Long id);

  List<SourceClient> getAllClients();
}
