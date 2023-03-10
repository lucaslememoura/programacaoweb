package br.com.lucaslememoura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class ProgramacaowebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramacaowebApplication.class, args);
	}

}
