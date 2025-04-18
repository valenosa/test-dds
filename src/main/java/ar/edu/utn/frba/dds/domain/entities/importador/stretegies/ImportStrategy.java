package ar.edu.utn.frba.dds.domain.entities.importador.stretegies;

import ar.edu.utn.frba.dds.domain.entities.Hecho;

import java.util.Set;

public interface ImportStrategy {
  Set<Hecho> importHechos();
}
