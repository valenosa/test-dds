package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.repositories.impl.EventRepository;
import ar.utn.edu.frba.ddsi.services.IConsensusAlgorithm;
import ar.utn.edu.frba.ddsi.services.IConsensusEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConsensusEvaluationService implements IConsensusEvaluationService {

    private final EventRepository eventRepo;
    private final List<IConsensusAlgorithm> strategies;

    @Autowired
    public ConsensusEvaluationService(EventRepository eventRepo,
                             List<IConsensusAlgorithm> strategies) {
        this.eventRepo = eventRepo;
        this.strategies = strategies;
    }

    // Cron: todos los días 2:00 AM
    @Scheduled(cron = "0 0 2 * * *")
   // @Transactional

    public void runVerification() {
        List<Event> allEvents = eventRepo.findAllEvents();

        strategies.forEach(s -> allEvents.forEach(s::apply));

        eventRepo.saveAll(allEvents);
    }


    //ALTERNATIVA CON FOLD
    /*
        @Scheduled(cron = "0 0 2 * * *")
    // @Transactional

        public void runVerification() {
        List<Event> allevents = eventRepo.findAll();

        List<Event> updated = allevents.stream()
                .map(event ->
                        strategies.stream()
                                // fold: iterativamente aplica cada estrategia sobre el mismo Hecho
                                .reduce(
                                        event,                                // estado inicial
                                        (e, strategy) -> strategy.apply(e),   // función de acumulación
                                        (e1, e2) -> e1                        // combiner, no se usa en secuencial
                                )
                )
                .collect(Collectors.toList());

        eventRepo.saveAll(updated);
    }
     */
}

