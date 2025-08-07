package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.entities.observer.Publisher;
import ar.utn.edu.frba.ddsi.models.entities.source.SourceFactory;
import ar.utn.edu.frba.ddsi.repositories.ISourceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DefaultSourceInitializer {

  private final SourceFactory sourceFactory;
  private final ISourceRepository sourceRepository;
  private final Publisher publisher;

  public DefaultSourceInitializer(SourceFactory sourceFactory, ISourceRepository sourceRepository, Publisher publisher) {
    this.sourceFactory = sourceFactory;
    this.sourceRepository = sourceRepository;
    this.publisher = publisher;
  }

  @PostConstruct
  public void initializeDefaultSources() {
    sourceRepository.save(sourceFactory.naturalDisaster());
  }
}
