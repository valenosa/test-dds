package ar.utn.edu.frba.ddsi.models.entities.source.impl;

import ar.utn.edu.frba.ddsi.models.entities.event.Event;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Category;
import ar.utn.edu.frba.ddsi.models.entities.event.values.Origin;
import ar.utn.edu.frba.ddsi.models.entities.source.IImporter;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class CsvImporter implements IImporter {

  public String getType() {
    return "CSV";
  }

  public Set<Event> importEvents(String fileName, Long sourceId) {

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("CSV/" + fileName);
    if (inputStream == null) {
      throw new RuntimeException("No se pudo encontrar el archivo: " + fileName);
    }

    Set<Event> events = new HashSet<>();

    try (
        CSVReader reader = new CSVReader(
            new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        )
    ) {

      String[] headers = reader.readNext();
      String[] row;

      while ((row = reader.readNext()) != null) {
        String title = row[0];
        String description = row[1];
        Category category = new Category(row[2]);
        Double latitude = Double.parseDouble(row[3]);
        Double longitude = Double.parseDouble(row[4]);
        LocalDateTime eventDate = null;
        Origin origin = Origin.STATIC;

        try {
          eventDate = LocalDate.parse(row[5], DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(); //TODO: Los csv tienen LocalDateTime o LocalDate?
        } catch (Exception e) {
          System.out.println("Invalid Date in row: " + Arrays.toString(row));
        }

        Event event = new Event(
            title,
            description,
            category,
            latitude,
            longitude,
            eventDate,
            origin,
            sourceId);

        events.add(event);
      }
    } catch (Exception e) {
      e.printStackTrace(); //TODO Cambiar esto por un método de logging más robusto
    }

    return events;
  }
}
