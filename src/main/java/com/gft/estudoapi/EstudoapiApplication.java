package com.gft.estudoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EstudoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstudoapiApplication.class, args);
	}

}
