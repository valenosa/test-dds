package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class SolicitudEliminacionDeHecho {

  private static final AtomicInteger contadorID = new AtomicInteger(1);
  @Getter
  private final Integer id;
  private final Hecho hecho;
  @Getter
  private final String justificacion;
  @Getter @Setter //!! Getter creado para que checklist no joda.
  private EstadoSolicitud estado;
  private final MetaDataSolicitudDeEliminacion metadata;

  public SolicitudEliminacionDeHecho(Hecho hecho, @NonNull String justificacion, String nombreSolicitante) {

    if (!this.esFundamentada(justificacion)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.id = contadorID.getAndIncrement();
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