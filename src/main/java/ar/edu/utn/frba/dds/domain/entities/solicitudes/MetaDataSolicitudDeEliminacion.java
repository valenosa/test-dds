package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import java.time.LocalDate;

public class MetaDataSolicitudDeEliminacion {
  private final LocalDate fechaSubida;
  private LocalDate fechaEvaluacion;
  private final String nombreSolicitante; //TODO luego será un usuario
  private String nombreEvaluador; //TODO luego será un usuario

  public MetaDataSolicitudDeEliminacion(String nombreSolicitante) {
    this.fechaSubida = LocalDate.now();
    this.nombreSolicitante = nombreSolicitante;
  }

  public void registrarEvaluacion(String nombreEvaluador) {
    this.fechaEvaluacion = LocalDate.now();
    this.nombreEvaluador = nombreEvaluador;
  }
}