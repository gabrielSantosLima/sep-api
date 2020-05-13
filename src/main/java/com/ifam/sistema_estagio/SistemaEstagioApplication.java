package com.ifam.sistema_estagio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SistemaEstagioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEstagioApplication.class, args);
	}
}
