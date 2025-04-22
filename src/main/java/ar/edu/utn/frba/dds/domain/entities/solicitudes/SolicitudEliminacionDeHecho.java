package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import ar.edu.utn.frba.dds.domain.entities.BaseDeDatos;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import lombok.Getter;
import lombok.NonNull;

public class SolicitudEliminacionDeHecho {

  private static Integer contadorId = 0; //? Cuándo implementemos la BD esto vuela?

  @Getter private final Integer id;
  private final String tituloHecho;
  @Getter private final String justificacion;

  public SolicitudEliminacionDeHecho(Hecho hecho, @NonNull String justificacion) {

    if (!this.esFundamentada(justificacion)) {
      throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
    }
    this.id = contadorId++;

    this.tituloHecho = hecho.getTitulo();
    this.justificacion = justificacion;

    BaseDeDatos.subirSolicitudDeEliminacion(this);
  }

  private boolean esFundamentada(String justificacion) {
    return justificacion.length() < 500;
  }

  public void eliminar() {
    BaseDeDatos.eliminarSolicitudDeEliminacion(this); //? Es preferible pasarle el objeto o directamente la id?
  }

  //TODO consultar que se hace con las solicitudes restantes de un mismo hecho cuando se acepta una de ellas
  public void aceptar() {
    Hecho hecho = BaseDeDatos.obtenerHecho(tituloHecho);
    hecho.setEliminado(true);
    BaseDeDatos.actualizarHecho(hecho);
    this.eliminar();
  }
}