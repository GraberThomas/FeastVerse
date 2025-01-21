package graber.thomas.feastverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FeastVerseApplication serves as the entry point for the FeastVerse application.
 * This class bootstraps the Spring Boot application using the SpringApplication.run method.
 * It is annotated with {@code @SpringBootApplication}, which encapsulates the components necessary
 * for Spring Boot applications such as component scanning and enabling auto-configuration.
 */
@SpringBootApplication
public class FeastVerseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeastVerseApplication.class, args);
    }

}