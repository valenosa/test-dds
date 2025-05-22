package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;

public interface ICollectionService {
  void create(CollectionCreationDTO collection);

}
