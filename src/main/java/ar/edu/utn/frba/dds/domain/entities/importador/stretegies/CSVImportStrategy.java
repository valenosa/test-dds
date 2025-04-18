package ar.edu.utn.frba.dds.domain.entities.importador.stretegies;

import ar.edu.utn.frba.dds.domain.entities.Hecho;
import com.opencsv.CSVReader;
import lombok.Setter;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSVImportStrategy implements ImportStrategy {

  @Setter
  private String rutaArchivoCsv;

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public Set<Hecho> importHechos() {

    Set<Hecho> hechos = new HashSet<>();

    try (CSVReader reader = new CSVReader(new FileReader(rutaArchivoCsv))) {
      String[] headers = reader.readNext(); // salteamos encabezado
      String[] fila;

      while ((fila = reader.readNext()) != null) {
        String titulo = fila[0];
        String descripcion = fila[1];
        String categoria = fila[2];
        Double latitud = Double.parseDouble(fila[3]);
        Double longitud = Double.parseDouble(fila[4]);
        LocalDate fechaAcontecimiento = null;
        LocalDate fechaCarga = LocalDate.now();

        try {
          fechaAcontecimiento = LocalDate.parse(fila[5], formatter);
        } catch (Exception e) {
          System.out.println("Fecha inválida para fila: " + Arrays.toString(fila));
        }


        Hecho hecho = new Hecho(titulo, descripcion, categoria, latitud, longitud, fechaAcontecimiento, fechaCarga);
        hechos.add(hecho);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return hechos;
  }
}
