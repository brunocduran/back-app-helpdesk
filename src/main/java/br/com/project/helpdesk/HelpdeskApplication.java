package br.com.project.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpdeskApplication {
	//implements CommandLineRunner -> executa um metodo sempre que a aplicacao incia
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}
}
