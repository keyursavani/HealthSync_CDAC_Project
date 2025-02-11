package com.user;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients
public class HealthSyncUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthSyncUserServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());
		return modelMapper;

	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder webClient() {
	    return WebClient.builder();
	}
//	
//	 @Bean
//	    public WebMvcConfigurer corsConfigurer() {
//	        return new WebMvcConfigurer() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/**")
//	                        .allowedOrigins("http://localhost:3000")
//	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//	                        .allowedHeaders("*")
//	                        .allowCredentials(true);
//	            }
//	        };
//	    }
//	 # Allow CORS for frontend
//	 spring.web.cors.allowed-origins=http://localhost:3000
//	 spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
//	 spring.web.cors.allowed-headers=*
//	 spring.web.cors.allow-credentials=true

}
