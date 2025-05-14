package org.ocean.multdatabases;

import org.ocean.multdatabases.repos.postgresql.PlantRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class
MultDatabasesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultDatabasesApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PlantRepository plantRepository) {
        return args -> {
            plantRepository.findAll().forEach(plant -> {
                System.out.println(plant.getName());
            });
        };
    }

}
