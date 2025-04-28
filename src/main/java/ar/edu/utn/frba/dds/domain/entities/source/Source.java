package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.report.Report;
import java.util.Set;
import lombok.Getter;

public class Source {

  @Getter
  public Set<Report> reports;
  private Importer importer;

  public Source(Importer importingStrategy) {
    importer = importingStrategy;
    reports = importer.importReports();
  }

}
