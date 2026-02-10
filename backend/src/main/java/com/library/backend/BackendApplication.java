package com.library.backend;

import com.library.backend.genre.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class);
    }

    /*
    @Bean
    public CommandLineRunner testGenre(GenreRepository repository) {
        return args -> {
            System.out.println("Genres in database:");
            repository.findAll().forEach(genre -> System.out.println(genre.getGenre()));
        };
    }

     */
}