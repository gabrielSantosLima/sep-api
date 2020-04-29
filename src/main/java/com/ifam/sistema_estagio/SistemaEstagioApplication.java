package com.ifam.sistema_estagio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SistemaEstagioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEstagioApplication.class, args);
	}
}
