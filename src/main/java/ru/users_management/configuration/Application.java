package ru.users_management.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan("ru")
@EntityScan("ru")
@EnableJpaRepositories("ru")
public class Application {
    public static void main(String[] args) {
        log.info("Microservice users_management starting...");
        SpringApplication.run(Application.class, args);
        log.info("Microservice users_management started.");
    }
}
