package com.saltlux.jiphyeonjeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
public class JiphyeonjeonApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiphyeonjeonApplication.class, args);
	}

}
