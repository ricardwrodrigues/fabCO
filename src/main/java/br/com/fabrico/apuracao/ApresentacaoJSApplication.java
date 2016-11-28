package br.com.fabrico.apuracao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApresentacaoJSApplication {

	private static final Logger log = LoggerFactory.getLogger(ApresentacaoJSApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ApresentacaoJSApplication.class, args);
		log.info("Acesse para visualizar : http://localhost:8080");

	}
}
