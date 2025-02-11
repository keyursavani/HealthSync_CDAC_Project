package com.api.gateway;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@SpringBootApplication
public class HealthSyncApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthSyncApiGateWayApplication.class, args);
	}
	

	  @Bean
	    public CorsWebFilter corsWebFilter() {
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.setAllowedOriginPatterns(List.of("http://localhost:3000", "https://yourdomain.com"));
	        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH"));
	        corsConfig.setAllowedHeaders(List.of("*"));
	        corsConfig.setAllowCredentials(true); // Allows cookies/authentication

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfig);

	        return new CorsWebFilter(source);
	    }

}
