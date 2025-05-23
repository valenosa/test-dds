package ar.utn.edu.frba.ddsi.schedulers;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CollectionRefreshScheduler {

    @PostConstruct
    public void startExecution(){
        //Actualizar coll
    }

    @Scheduled(cron = "${collection.refresh.cron}")
    public void refreshCollections() {
        //Actualizar coll
    }
}
