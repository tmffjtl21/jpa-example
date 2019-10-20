package com.osstem.jpa.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class JpaExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApplication.class, args);
    }

}
