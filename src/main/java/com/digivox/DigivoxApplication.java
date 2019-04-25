package com.digivox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.digivox.controllers")
public class DigivoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigivoxApplication.class, args);
	}

}
