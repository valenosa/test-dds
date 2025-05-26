package ar.utn.edu.frba.ddsi.services;

import java.time.LocalDateTime;

public interface ISourceService {
  void initSources();

  void refreshSources(LocalDateTime lastUpdate);
}
