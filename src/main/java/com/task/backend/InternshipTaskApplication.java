package com.task.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InternshipTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternshipTaskApplication.class, args);
	}

	@Bean
	ModelMapper modelMappper()
	{
		return new ModelMapper();
	}
}
