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
    sourceRepository.findAll().forEach(publisher::notifyNewSource); // Cuando metamos persistencia, va a notificar todas las que ya existan (incluso las que no son default), lo cual me parece bien. Habría que implementar una forma de bajar las que sean dinámicas
  }
}
