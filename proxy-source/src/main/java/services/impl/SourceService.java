package services.impl;

import models.dtos.output.EventOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ISourceRepository;
import services.ISourceService;

import java.util.List;

@Service
public class SourceService implements ISourceService {

  @Autowired
  ISourceRepository sourceRepository;

  @Override
  public List<EventOutputDTO> getEvents() {
    return sourceRepository.findAll()
        .stream()
        .flatMap(source -> source.fetchEvents().stream())
        .map(EventOutputDTO::from)
        .toList();
  }
}
