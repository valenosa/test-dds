package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ISourceClientRepository {
  SourceClient save(SourceClient sourceClient);

  SourceClient findById(Long id);

  List<SourceClient> getAllClients();
}
