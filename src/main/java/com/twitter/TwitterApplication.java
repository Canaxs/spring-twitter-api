package com.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@EnableCaching
@SpringBootApplication
public class TwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String description, @Value ("${application-version}") String version) {
		return new OpenAPI()
				.info(new Info()
				.title("Twitter-Api")
				.version(version)
				.description(description)
				.license(new License().name("Twitter-Api License")));
		
	}
}
