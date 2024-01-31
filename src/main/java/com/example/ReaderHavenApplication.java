package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.repository")
@EnableAutoConfiguration(exclude = {
	    JmxAutoConfiguration.class
	})
public class ReaderHavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReaderHavenApplication.class, args);
	}

}
