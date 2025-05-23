package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;

import java.util.List;

public interface ICollectionService {
    void create(CollectionCreationDTO collection);

    List<CollectionOutputDTO> getCollections();
}
