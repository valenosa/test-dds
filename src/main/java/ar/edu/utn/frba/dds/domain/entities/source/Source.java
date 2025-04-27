package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.fact.Fact;
import java.util.Set;
import lombok.Getter;

public class Source {

  @Getter
  public Set<Fact> facts;
  private Importer importer;

  public Source(Importer importingStrategy) {
    importer = importingStrategy;
    facts = importer.importFacts();
  }

}
