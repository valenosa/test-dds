package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import ar.edu.utn.frba.dds.domain.entities.importador.stretegies.ImportStrategy;
import ar.edu.utn.frba.dds.domain.entities.importador.stretegies.CSVImportStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

//todo: Escenario 2:  Importación de hechos por csv
//“Como persona administradora, deseo poder importar hechos desde un archivo CSV”.
//Se espera que los equipos puedan mostrar mediante un main de prueba o tests unitarios que se puede importar un csv correctamente
// (se pueden utilizar los archivos de prueba provistos por la Cátedra). Esto implica realizar la lectura de un archivo correctamente
// (utilizando alguna biblioteca para tal fin) y, luego, validar que se instancian correctamente los hechos.

public class TestFuenteEstatica {

  private CSVImportStrategy desastresNaturalesArgentina;
  Set<Hecho> hechosImportados;


  @BeforeEach
  public void setUp() {
    desastresNaturalesArgentina = new CSVImportStrategy();
    desastresNaturalesArgentina.setRutaArchivoCsv("src/test/resources/fuente.csv"); // TODO: Después cambiar la ruta

    hechosImportados = new HashSet<>();
  }

  @Test
  public void testImportacionHechosViaCSV() {

    hechosImportados = desastresNaturalesArgentina.importHechos();

    String tituloEsperado = "Ráfagas de más de 100 km/h causa estragos en San Vicente, Misiones";
    String descripcionEsperada = "La región de San Vicente en Misiones sufrió los efectos de una intensa ráfagas de más de 100 km/h. El incidente obligando a evacuar a residentes de la zona. Se ha convocado al comité de crisis para coordinar las acciones de respuesta.";
    String categoriaEsperada = "Ráfagas de más de 100 km/h";
    Double latitudEsperada = -27.029465; // Esto tiene que ser double
    Double longitudEsperada = -54.436559;
    LocalDate fechaAcontecimientoEsperada = LocalDate.parse("21/12/2007", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate fechaCargaEsperada = LocalDate.now();

    Hecho hechoEsperado = new Hecho(tituloEsperado, descripcionEsperada, categoriaEsperada, latitudEsperada, longitudEsperada, fechaAcontecimientoEsperada, fechaCargaEsperada);

    // Usar un iterador para obtener el primer elemento del Set
    Iterator<Hecho> iterator = hechosImportados.iterator();

    if (!iterator.hasNext()) {
      throw new NoSuchElementException("No hay elementos en el Set de hechos importados");
    }

    Hecho primerHecho = iterator.next();
    Assertions.assertEquals(hechoEsperado, primerHecho);
  }
}