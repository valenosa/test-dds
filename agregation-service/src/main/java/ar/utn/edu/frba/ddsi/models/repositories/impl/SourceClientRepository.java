package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.sourceClient.SourceClient;
import ar.utn.edu.frba.ddsi.models.repositories.ISourceClientRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class SourceClientRepository implements ISourceClientRepository {
  Map<String, SourceClient> sourceClients = new HashMap<>(); //<url,sourceClient>

  @Override
  public void save(SourceClient sourceClient) {
    if (sourceClient.getUrl() == null || sourceClient.getUrl().isEmpty()) {
      throw new IllegalArgumentException("SourceClient URL cannot be null or empty");
    }
    sourceClients.put(sourceClient.getUrl(), sourceClient);
  }

  public List<SourceClient> getAllClients() {
    return sourceClients.values().stream().toList();
  }
}
