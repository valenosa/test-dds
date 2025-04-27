package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.util.Set;

public interface Importer {
  public Set<Fact>  importFacts();
}
