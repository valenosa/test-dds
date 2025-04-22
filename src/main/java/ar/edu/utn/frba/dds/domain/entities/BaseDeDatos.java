package ar.edu.utn.frba.dds.domain.entities;

import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.solicitudes.SolicitudEliminacionDeHecho;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Clase que simula una base de datos en memoria para gestionar hechos y solicitudes de eliminación.
 * Contiene estructuras estáticas para almacenar hechos y solicitudes, y métodos utilitarios para acceder y modificar los datos.
 */
public class BaseDeDatos {

  public static Map<String, Hecho> hechos = new HashMap<>(); //<Titulo, Hecho>
  public static Map<Integer, SolicitudEliminacionDeHecho> solicitudes = new HashMap<>();

  /**
   * Agrega un conjunto de nuevos hechos al sistema.
   *
   * @param nuevosHechos Conjunto de hechos a agregar.
   */
  public static void subirHechos(Set<Hecho> nuevosHechos) {
    hechos.putAll(
        //Set -> Map
        nuevosHechos.stream().collect(Collectors.toMap(Hecho::getTitulo, Function.identity()))
    );
  }

  /**
   * Devuelve los hechos especificados por título, <b>siempre que no estén marcados como eliminados</b>.
   *
   * @param nombresHechos Títulos de los hechos a buscar.
   * @return Conjunto de hechos no eliminados.
   */
  public static Set<Hecho> obtenerHechos(Set<String> nombresHechos) {
    return nombresHechos.stream().map(hechos::get).filter((hecho) -> !hecho.isEliminado()).collect(Collectors.toSet());
  }

  /**
   * Devuelve un hecho específico por su título.
   *
   * @param tituloHecho Título del hecho.
   * @return Hecho correspondiente o null si no existe.
   */
  public static Hecho obtenerHecho(String tituloHecho) {
    return hechos.get(tituloHecho);
  }

  /**
   * Actualiza o reemplaza un hecho en el sistema.
   *
   * @param hecho Hecho actualizado.
   */
  public static void actualizarHecho(Hecho hecho) {
    hechos.put(hecho.getTitulo(), hecho);
  }

  /**
   * Agrega una nueva solicitud de eliminación de hecho.
   *
   * @param solicitud Solicitud a agregar.
   */
  public static void subirSolicitudDeEliminacion(SolicitudEliminacionDeHecho solicitud) {
    solicitudes.put(solicitud.getId(), solicitud);
  }

  /**
   * Elimina una solicitud de eliminación existente.
   *
   * @param solicitud Solicitud a eliminar.
   */
  public static void eliminarSolicitudDeEliminacion(SolicitudEliminacionDeHecho solicitud) {
    solicitudes.remove(solicitud.getId());
  }
}
