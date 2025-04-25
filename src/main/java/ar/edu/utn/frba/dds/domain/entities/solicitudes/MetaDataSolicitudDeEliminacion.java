package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import java.time.LocalDate;

public class MetaDataSolicitudDeEliminacion {
  //? En un futuro quizás sea necesario agregar getters a los final.
  private final LocalDate fechaSubida = LocalDate.now();
  private final String nombreSolicitante; //TODO luego será un usuario
  private LocalDate fechaEvaluacion;
  private String nombreEvaluador; //TODO luego será un usuario

  public MetaDataSolicitudDeEliminacion(String nombreSolicitante) {
    this.nombreSolicitante = nombreSolicitante;
  }

  public void registrarEvaluacion(String nombreEvaluador) {
    this.fechaEvaluacion = LocalDate.now();
    this.nombreEvaluador = nombreEvaluador;
  }
}