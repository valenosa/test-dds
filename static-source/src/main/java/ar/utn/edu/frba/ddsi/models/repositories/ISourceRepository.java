package ar.utn.edu.frba.ddsi.models.repositories;

import ar.utn.edu.frba.ddsi.models.entities.source.Source;

public interface ISourceRepository {
  void save(Source source);
  Source findById(Long id);
}
