package ar.utn.edu.frba.ddsi.models.repositories.impl;

import ar.utn.edu.frba.ddsi.models.entities.collections.Collection;
import ar.utn.edu.frba.ddsi.models.repositories.ICollectionRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionRepository implements ICollectionRepository {

  Map<String, Collection> collections = new HashMap<>();

  @Override
  public void save(Collection collection) {
    if (collection.getHandler() == null) {
      String handler = collection.getTitle(); //TODO: verificar que es realmente el handler (por ahora tomamos el titulo de la coleccion)
      collection.setHandler(handler);
      collections.put(handler, collection);
    } else {
      collections.put(collection.getHandler(), collection);
    }
  }

  @Override
  public List<Collection> findAll() {
    return new ArrayList<>(collections.values());
  }

  @Override
  public Collection findByHandler(String handler) {
    return collections.get(handler);
  }
}