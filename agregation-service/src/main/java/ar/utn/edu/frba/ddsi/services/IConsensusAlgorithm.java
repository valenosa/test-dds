package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Consensus;

public interface IConsensusAlgorithm {
    Consensus getAlgorithmType();

    Event apply(Event event);
}
