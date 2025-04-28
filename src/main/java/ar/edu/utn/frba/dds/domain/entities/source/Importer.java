package ar.edu.utn.frba.dds.domain.entities.source;

import ar.edu.utn.frba.dds.domain.entities.report.Report;
import java.util.Set;

public interface Importer {
  public Set<Report> importReports();
}
