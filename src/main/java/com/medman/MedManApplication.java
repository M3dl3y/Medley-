package com.medman;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MedManApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(MedManApplication.class, args);

	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MedManApplication.class);
	}
}