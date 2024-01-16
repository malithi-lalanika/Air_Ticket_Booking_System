package com.fullstack.B_Airways_Backend;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import java.security.SecureRandom;

@SpringBootApplication
public class BAirwaysBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BAirwaysBackendApplication.class, args);
		//System.out.println("Hii");
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}






}
