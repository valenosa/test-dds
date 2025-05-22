package ar.utn.edu.frba.ddsi.schedulers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CollectionRefreshScheduler {

    // Inyecta los repositorios o servicios necesarios

    // CollectionRefreshService.java
    public void refreshMetaMapaCollections() {
        // Lógica para actualizar o consultar fuentes MetaMapa en tiempo real
    }

    @Value("${collection.refresh.cron}")
    private String cronExpression;

    // Ejecuta cada hora, excluyendo MetaMapa
    @Scheduled(cron = "${collection.refresh.cron}") // cada hora en punto
    public void refreshNonMetaMapaCollections() {
        // 1. Obtén todas las colecciones de fuentes proxy NO MetaMapa
        // 2. Actualiza los hechos de cada colección desde la API externa
        // 3. Guarda los cambios
    }
}
