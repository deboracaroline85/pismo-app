package com.pismo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EntityScan("com.pismo.entity")
public class PismoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PismoAppApplication.class, args);
	}

}
