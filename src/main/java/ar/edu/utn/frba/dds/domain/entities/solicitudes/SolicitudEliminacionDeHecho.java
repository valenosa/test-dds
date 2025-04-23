package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import ar.edu.utn.frba.dds.domain.entities.BaseDeDatos;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.NonNull;

public class SolicitudEliminacionDeHecho {

  private static final AtomicInteger contadorID = new AtomicInteger(1);
  @Getter
  private final Integer id;
  private final String tituloHecho;
  @Getter
  private final String justificacion;

  public SolicitudEliminacionDeHecho(Hecho hecho, @NonNull String justificacion) {

    if (!this.esFundamentada(justificacion)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.id = contadorID.getAndIncrement();

    this.tituloHecho = hecho.getTitulo();
    this.justificacion = justificacion;

    BaseDeDatos db = BaseDeDatos.getInstance();
    db.subirSolicitudDeEliminacion(this);
  }

  private boolean esFundamentada(String justificacion) {
    return justificacion.length() >= 500;
  }

  public void eliminar() {
    //? Es preferible pasarle el objeto o directamente la id?
    BaseDeDatos db = BaseDeDatos.getInstance();
    db.eliminarSolicitudDeEliminacion(this);
  }

  /*TODO consultar que se hace con las solicitudes restantes
     de un mismo hecho cuando se acepta una de ellas */
  public void aceptar() {
    BaseDeDatos db = BaseDeDatos.getInstance();
    Hecho hecho = db.obtenerHecho(tituloHecho);
    hecho.setEliminado(true);
    db.actualizarHecho(hecho);
    this.eliminar();
  }

  public void rechazar() {

  }
}