package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import lombok.Getter;

public class SolicitudEliminacionDeHecho {

  private Integer id;
  private Hecho hecho;
  private String justificacion;
  @Getter
  private EstadoSolicitud estado;

  public SolicitudEliminacionDeHecho(Hecho hecho, String justificacion) {

    if (justificacion == null || justificacion.length() < 500) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.hecho = hecho;
    this.justificacion = justificacion;
    this.estado = EstadoSolicitud.PENDIENTE;
  }

  public void rechazar() {
    this.estado = EstadoSolicitud.RECHAZADA;
  }

  //Recordemos que, o bien cuando pasen 24 horas o cuando un admin lo decida, deberiamos activar este método.
  public void aceptar() {
    this.estado = EstadoSolicitud.ACEPTADA;
    this.hecho.setEliminado(true);
  }
}
