package ar.edu.utn.frba.dds.domain.entities.fuente;

import ar.edu.utn.frba.dds.domain.entities.hecho.Categoria;
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
import lombok.Getter;

public class Fuente {

  @Getter
  public Set<Hecho> hechos;
  private final String rutaArchivoCsv;

  //--- Constructor
  public Fuente(String rutaArchivoCsv) {
    this.rutaArchivoCsv = rutaArchivoCsv;
    hechos = importHechos();
  }

  //--- Importar Hechos desde CSV
  private Set<Hecho> importHechos() {

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
        Categoria categoria = new Categoria(fila[2]);
        Double latitud = Double.parseDouble(fila[3]);
        Double longitud = Double.parseDouble(fila[4]);
        LocalDate fechaAcontecimiento = null;
        LocalDate fechaCarga = LocalDate.now();
        Origen origen = Origen.DATASET;

        try {
          fechaAcontecimiento = LocalDate.parse(fila[5], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
