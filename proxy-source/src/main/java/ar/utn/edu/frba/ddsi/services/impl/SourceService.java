package ar.utn.edu.frba.ddsi.services.impl;

import ar.utn.edu.frba.ddsi.models.dtos.input.SourceInputDTO;
import ar.utn.edu.frba.ddsi.models.entities.observer.Publisher;
import ar.utn.edu.frba.ddsi.models.entities.source.impl.Source;
import ar.utn.edu.frba.ddsi.repositories.ISourceRepository;
import ar.utn.edu.frba.ddsi.services.ISourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

  @Autowired
  ISourceRepository sourceRepository;

  @Autowired
  Publisher publisher;

  @Override
  public void create(SourceInputDTO dto) {
    Source source = Source.from(dto);
    sourceRepository.save(source);
    publisher.notifyNewSource(source);
  }

  public void getEventsBySourceId(Long id) {
    //TODO
  }
}
