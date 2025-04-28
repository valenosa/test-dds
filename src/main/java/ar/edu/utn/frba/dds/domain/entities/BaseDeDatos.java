package ar.edu.utn.frba.dds.domain.entities;

import ar.edu.utn.frba.dds.domain.entities.event.Event;
import ar.edu.utn.frba.dds.domain.entities.requests.DeletionRequest;
import java.util.HashMap;
import java.util.HashSet;
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

  private final Map<String, Event> hechos; //<Titulo, Hecho>
  private final Map<Integer, DeletionRequest> solicitudes;

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
   * @param nuevosEvents Conjunto de hechos a agregar.
   */
  public void subirHechos(Set<Event> nuevosEvents) {
    hechos.putAll(
        //Set -> Map
        nuevosEvents.stream().collect(Collectors.toMap(Event::getTitle, Function.identity()))
    );
  }

  /**
   * Devuelve los hechos especificados por título,
   * <b>siempre que no estén marcados como eliminados</b>.
   *
   * @param nombresHechos Títulos de los hechos a buscar.
   * @return Conjunto de hechos no eliminados.
   */
  public Set<Event> obtenerHechos(Set<String> nombresHechos) {
    return nombresHechos.stream().map(hechos::get).filter(
        (hecho) -> !hecho.isDeleted()).collect(Collectors.toSet());
  }

  /**
   * Devuelve todos los hechos de la DB,
   * <b>siempre que no estén marcados como eliminados</b>.
   *
   * @return Conjunto de hechos no eliminados.
   */
  public Set<Event> obtenerHechos() {
    return new HashSet<>(hechos.values());
  }

  /**
   * Devuelve un hecho específico por su título.
   *
   * @param tituloHecho Título del hecho.
   * @return Hecho correspondiente o null si no existe.
   */
  public Event obtenerHecho(String tituloHecho) {
    //TODO validar que el hecho no haya sido eliminado
    return hechos.get(tituloHecho);
  }

  /**
   * Actualiza o reemplaza un hecho en el sistema.
   *
   * @param event Hecho actualizado.
   */
  public void actualizarHecho(Event event) {
    hechos.put(event.getTitle(), event);
  }

}
