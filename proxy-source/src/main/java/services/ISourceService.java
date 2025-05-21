package services;

import models.dtos.output.EventOutputDTO;

import java.util.List;

public interface ISourceService {
  List<EventOutputDTO> getEvents();
}
