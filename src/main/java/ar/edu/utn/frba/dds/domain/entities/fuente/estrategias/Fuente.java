package ar.edu.utn.frba.dds.domain.entities.fuente.estrategias;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;

import java.util.Set;

public interface Fuente {
  Set<Hecho> importHechos();
}
