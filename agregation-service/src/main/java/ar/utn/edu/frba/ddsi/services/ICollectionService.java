package ar.utn.edu.frba.ddsi.services;

import ar.utn.edu.frba.ddsi.models.dtos.input.CollectionCreationDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.CollectionOutputDTO;
import ar.utn.edu.frba.ddsi.models.dtos.output.EventOutputDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface ICollectionService {
  void create(CollectionCreationDTO collectionDto);

  List<CollectionOutputDTO> getCollections();

  List<EventOutputDTO> getEventsFromCollection(
      String handler,
      String category,
      LocalDateTime untilUploadDate,
      LocalDateTime fromUploadDate,
      LocalDateTime untilEventDate,
      LocalDateTime fromEventDate
  );

  void refreshCollections(LocalDateTime lastUpdate);
}
