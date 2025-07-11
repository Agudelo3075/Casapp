package com.ensayo.casapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CasappApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
			.ignoreIfMissing()
			.load();

		if(dotenv!=null){
			dotenv.entries().forEach(entry -> {
				if(System.getProperty(entry.getKey()) == null && System.getenv(entry.getKey())==null){
					System.setProperty(entry.getKey(), entry.getValue());
				}
			});
		}
		SpringApplication.run(CasappApplication.class, args);
	}

}
