package com.bigboss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI bigBossApi() {
		return new OpenAPI()
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("BigBoss Voting API")
						.description("BigBoss AI Voting application")
						.version("V1"));
	}

}
