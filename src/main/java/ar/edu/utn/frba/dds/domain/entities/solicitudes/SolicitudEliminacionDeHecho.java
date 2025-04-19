package ar.edu.utn.frba.dds.domain.entities.solicitudes;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import lombok.Getter;

public class SolicitudEliminacionDeHecho {

    private Integer id;
    private Hecho hecho;
    private String justificacion;
    @Getter
    private EstadoSolicitud estado;

    //REQUISITO 1
    public SolicitudEliminacionDeHecho(Hecho hecho, String justificacion) {
        //REQUISITO 2
        if (justificacion == null || justificacion.length() < 500) {
            throw new IllegalArgumentException("La justificación debe tener al menos 500 caracteres.");
        }
        this.hecho = hecho;
        this.justificacion = justificacion;
        this.estado = EstadoSolicitud.PENDIENTE;
        hecho.addSolicitudEliminacionDeHecho(this);
    }

    public void rechazar() {
        this.estado = EstadoSolicitud.RECHAZADA;
        //TODO actualizar la solicitud en la bbdd
    }

    //Recordemos que, o bien cuando pasen 24 horas o cuando un admin lo decida, deberiamos activar este metodo.
    public void aceptar() {
        this.estado = EstadoSolicitud.ACEPTADA;
        this.hecho.setEliminado(true);
        //TODO actualizar la solicitud en la bbdd
    }
}

//Map<Id_Hecho, Id_Solicitud>

