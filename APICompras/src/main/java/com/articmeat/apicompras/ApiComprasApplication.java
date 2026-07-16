package com.articmeat.apicompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class ApiComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiComprasApplication.class, args);
	}
	
	@Bean
	public WebMvConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
		};
	}
}
