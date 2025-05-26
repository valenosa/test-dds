package ar.utn.edu.frba.ddsi.repositories;

import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;

import java.util.List;

public interface ISourceRepository {
  void save(Source source);
  List<Source> findAll();
  Source findById(Long id);
}
