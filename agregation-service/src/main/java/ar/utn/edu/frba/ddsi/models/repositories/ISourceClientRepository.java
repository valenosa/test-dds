package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.source.SourceClient;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ISourceClientRepository {
  SourceClient save(SourceClient sourceClient);

  List<SourceClient> getAllClients();
}
