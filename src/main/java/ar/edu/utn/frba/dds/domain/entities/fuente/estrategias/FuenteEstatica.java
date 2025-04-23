package ar.edu.utn.frba.dds.domain.entities.fuente.estrategias;

import ar.edu.utn.frba.dds.domain.entities.BaseDeDatos;
import ar.edu.utn.frba.dds.domain.entities.hecho.Hecho;
import ar.edu.utn.frba.dds.domain.entities.hecho.Origen;
import com.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FuenteEstatica extends Fuente {

  private final String rutaArchivoCsv;

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  //--- Constructor
  public FuenteEstatica(String rutaArchivoCsv) {
    this.rutaArchivoCsv = rutaArchivoCsv;

    // TODO Delegar al padre👶
    Set<Hecho> hechosImportados = importHechos();

    BaseDeDatos db = BaseDeDatos.getInstance();

    db.subirHechos(hechosImportados);

    this.nombresHechosAsociados = nombresHechos(hechosImportados);
  }

  //--- Importar Hechos
  @Override
  public Set<Hecho> importHechos() {

    Set<Hecho> hechos = new HashSet<>();

    try (
        CSVReader reader = new CSVReader(
            new InputStreamReader(new FileInputStream(rutaArchivoCsv), StandardCharsets.UTF_8)
        )
    ) {

      String[] headers = reader.readNext();
      String[] fila;

      while ((fila = reader.readNext()) != null) {
        String titulo = fila[0];
        String descripcion = fila[1];
        String categoria = fila[2];
        Double latitud = Double.parseDouble(fila[3]);
        Double longitud = Double.parseDouble(fila[4]);
        LocalDate fechaAcontecimiento = null;
        LocalDate fechaCarga = LocalDate.now();
        Origen origen = Origen.DATASET;

        try {
          fechaAcontecimiento = LocalDate.parse(fila[5], formatter);
        } catch (Exception e) {
          System.out.println("Fecha inválida para fila: " + Arrays.toString(fila));
        }


        Hecho hecho = new Hecho(
            titulo,
            descripcion,
            categoria,
            latitud,
            longitud,
            fechaAcontecimiento,
            fechaCarga,
            origen);

        hechos.add(hecho);
      }
    } catch (Exception e) {
      e.printStackTrace(); //TODO Cambiar esto por un método de logging más robusto
    }

    return hechos;
  }
}
