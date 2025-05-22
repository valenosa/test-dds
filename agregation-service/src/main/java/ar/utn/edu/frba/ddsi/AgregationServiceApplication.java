package ar.utn.edu.frba.ddsi;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class AgregationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgregationServiceApplication.class, args);
    }
}
