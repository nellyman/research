package com.nbh.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active",
				"security,authorization,frpjava");
		SpringApplication.run(ReactiveApplication.class, args);
	}
}
