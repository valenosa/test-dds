package repositories;

import models.entities.source.impl.Source;

import java.util.List;

public interface ISourceRepository {
  void save(Source source);
  List<Source> findAll();
  Source findById(Long id);
}
