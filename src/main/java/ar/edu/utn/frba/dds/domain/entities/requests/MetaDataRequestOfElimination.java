package ar.edu.utn.frba.dds.domain.entities.requests;

import java.time.LocalDate;

public class MetaDataRequestOfElimination {
  private final LocalDate uploadDate;
  private LocalDate evaluationDate;
  private final String applicantName; //TODO luego será un usuario
  private String evaluatorName; //TODO luego será un usuario

  public MetaDataRequestOfElimination(String applicantName) {
    this.uploadDate = LocalDate.now();
    this.applicantName = applicantName;
  }

  public void registerEvaluation(String evaluatorName) {
    this.evaluationDate = LocalDate.now();
    this.evaluatorName = evaluatorName;
  }
}