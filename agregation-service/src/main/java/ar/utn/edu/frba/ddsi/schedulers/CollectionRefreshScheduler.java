package ar.utn.edu.frba.ddsi.schedulers;

import ar.utn.edu.frba.ddsi.services.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CollectionRefreshScheduler {

    @Autowired
    ICollectionService collectionService;

    @Scheduled(cron = "${collection.refresh.cron}")
    public void refreshCollections() {

        collectionService.refreshCollections();
    }
}
