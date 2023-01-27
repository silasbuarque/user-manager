package com.usuario.gerenciamentodeusuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class GerenciamentoDeUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoDeUsuarioApplication.class, args);
	}

}
