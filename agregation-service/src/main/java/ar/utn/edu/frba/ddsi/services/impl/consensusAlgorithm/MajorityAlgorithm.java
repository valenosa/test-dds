package ar.utn.edu.frba.ddsi.services.impl.consensusAlgorithm;

import ar.utn.edu.frba.ddsi.models.entities.collections.Collection;
import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Consensus;
import ar.utn.edu.frba.ddsi.models.repositories.impl.CollectionRepository;
import ar.utn.edu.frba.ddsi.models.repositories.impl.EventRepository;
import ar.utn.edu.frba.ddsi.models.repositories.impl.SourceClientRepository;
import ar.utn.edu.frba.ddsi.services.IConsensusAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MajorityAlgorithm implements IConsensusAlgorithm {

    private final SourceClientRepository sourceRepo;
    private long totalSources;

    @Autowired
    public MajorityAlgorithm(SourceClientRepository sourceRepo) {
        this.sourceRepo = sourceRepo;
        this.totalSources = -1; // inicialización lazy

    }

    @Override
    public Consensus getAlgorithmType() {
        return Consensus.MAJORITY;
    }

    @Override
    public Event apply(Event event) {

        if (totalSources < 0) {
            totalSources = sourceRepo.getAllClients().size();
        }

        if (totalSources == 0) return event; //? Deberia ser un error?

        /*
        int mentions = event.getMentionedSources().size();

        boolean cumple = mentions >= Math.ceil(totalSources / 2.0);

        if (cumple) {
            event.getConsensus().add(getAlgorithmType());
        } else {
            event.getConsensus().remove(getAlgorithmType());
        }

        */
        return event;

    }

}
