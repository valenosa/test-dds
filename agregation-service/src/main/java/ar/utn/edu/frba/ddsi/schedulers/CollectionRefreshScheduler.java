package ar.utn.edu.frba.ddsi.schedulers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CollectionRefreshScheduler {

    @Value("${collection.refresh.cron}")
    private String cronExpression;

    // TODO: Inyectar dependencias

    public void refreshMetaMapaCollections() {
        // TODO
        //Chequear Origen fuente (Instancia metamapa)
        //this.collectionService.update();
    }

    // Ejecuta cada hora, excluyendo MetaMapa
    @Scheduled(cron = "${collection.refresh.cron}") // cada hora en punto
    public void refreshNonMetaMapaCollections() {
        // TODO
        //Chequear Origen fuente (Proxy o API)
        //this.collectionService.update();
    }

    //! Seguramente estas funciones se puedan unificar
}
