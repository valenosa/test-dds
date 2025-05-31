package ar.utn.edu.frba.ddsi.schedulers;

import ar.utn.edu.frba.ddsi.services.ICollectionService;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CollectionRefreshScheduler {

  @Autowired
  ICollectionService collectionService;

  @Autowired
  ISourceService sourceService;

  LocalDateTime lastUpdate = LocalDateTime.now();

  @Scheduled(cron = "${collection.refresh.cron}")
  public void refreshCollections() {
    sourceService.refreshSources(lastUpdate);

    collectionService.refreshCollections(lastUpdate);

    lastUpdate = LocalDateTime.now();
  }
}
