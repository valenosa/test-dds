package ar.edu.utn.frba.dds.domain.entities;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.SolicitudEliminacionDeHecho;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Singleton que simula una base de datos en memoria para gestionar hechos y solicitudes
 * de eliminación.
 * Contiene estructuras estáticas para almacenar hechos y solicitudes,
 * y métodos utilitarios para acceder y modificar los datos.
 */
public class BaseDeDatos {

  // Instancia única del Singleton.
  private static BaseDeDatos instancia;

  private final Map<String, Hecho> hechos; //<Titulo, Hecho>
  private final Map<Integer, SolicitudEliminacionDeHecho> solicitudes;

  private BaseDeDatos() {
    this.hechos = new HashMap<>();
    this.solicitudes = new HashMap<>();
  }

  /**
   * Método de acceso global a la instancia única de la clase.
   *
   * @return Instancia única de BaseDeDatos.
   */

  public static BaseDeDatos getInstance() {
    if (instancia == null) {
      instancia = new BaseDeDatos();
    }
    return instancia;
  }

  /**
   * Agrega un conjunto de nuevos hechos al sistema.
   *
   * @param nuevosHechos Conjunto de hechos a agregar.
   */
  public void subirHechos(Set<Hecho> nuevosHechos) {
    hechos.putAll(
        //Set -> Map
        nuevosHechos.stream().collect(Collectors.toMap(Hecho::getTitulo, Function.identity()))
    );
  }

  /**
   * Devuelve los hechos especificados por título,
   * <b>siempre que no estén marcados como eliminados</b>.
   *
   * @param nombresHechos Títulos de los hechos a buscar.
   * @return Conjunto de hechos no eliminados.
   */
  public Set<Hecho> obtenerHechos(Set<String> nombresHechos) {
    return nombresHechos.stream().map(hechos::get).filter(
        (hecho) -> !hecho.isEliminado()).collect(Collectors.toSet());
  }

  /**
   * Devuelve un hecho específico por su título.
   *
   * @param tituloHecho Título del hecho.
   * @return Hecho correspondiente o null si no existe.
   */
  public Hecho obtenerHecho(String tituloHecho) {
    return hechos.get(tituloHecho);
  }

  /**
   * Actualiza o reemplaza un hecho en el sistema.
   *
   * @param hecho Hecho actualizado.
   */
  public void actualizarHecho(Hecho hecho) {
    hechos.put(hecho.getTitulo(), hecho);
  }

  /**
   * Agrega una nueva solicitud de eliminación de hecho.
   *
   * @param solicitud Solicitud a agregar.
   */
  public void subirSolicitudDeEliminacion(SolicitudEliminacionDeHecho solicitud) {
    solicitudes.put(solicitud.getId(), solicitud);
  }

  /**
   * Elimina una solicitud de eliminación existente.
   *
   * @param solicitud Solicitud a eliminar.
   */
  public void eliminarSolicitudDeEliminacion(SolicitudEliminacionDeHecho solicitud) {
    solicitudes.remove(solicitud.getId());
  }
}
