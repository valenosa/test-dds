package ar.utn.edu.frba.ddsi.models.entities.scheduler;

import ar.utn.edu.frba.ddsi.models.entities.observer.Publisher;
import ar.utn.edu.frba.ddsi.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventScheduler {

  @Autowired
  Publisher publisher;

  @Autowired
  IEventService eventService;

  LocalDateTime lastUpdate = LocalDateTime.now();

  @Scheduled(cron = "${event.notify.cron}")
  public void notifyNewEvents() {
    eventService.notifyEvents(lastUpdate);
    lastUpdate = LocalDateTime.now();
  }
}
