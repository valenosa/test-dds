package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import lombok.Getter;

public class SolicitudEliminacion {

  @Getter
  private final Hecho hecho;
  @Getter
  private final String justificacion;
  @Getter
  private EstadoSolicitud estado;
  private final MetaDataSolicitudDeEliminacion metadata;

  public SolicitudEliminacion(Hecho hecho, String justificacion, String nombreSolicitante) {

    if (!this.esFundamentada(justificacion)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.hecho = hecho;
    this.justificacion = justificacion;
    this.estado = EstadoSolicitud.PENDIENTE;
    this.metadata = new MetaDataSolicitudDeEliminacion(nombreSolicitante);
  }

  private boolean esFundamentada(String justificacion) {
    return justificacion.length() >= 500;
  }

  public void aceptar(String nombreEvaluador) {
    this.metadata.registrarEvaluacion(nombreEvaluador);
    this.estado = EstadoSolicitud.ACEPTADA;
    hecho.setEliminado(true);
  }

  public void rechazar(String nombreEvaluador) {
    this.metadata.registrarEvaluacion(nombreEvaluador);
    this.estado = EstadoSolicitud.RECHAZADA;
  }
}