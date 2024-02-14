package com.swr.petweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.swr.petweb.entity.Post;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PetwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetwebApplication.class, args);
	}

}
