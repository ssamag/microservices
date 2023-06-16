package com.spring.microservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableFeignClients
//@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "Employee Service REST API Documentation", description = "Employee Service REST API Documentation", version = "v1.0", contact = @Contact(name = "Sandip Samag", email = "samagsandip@gmail.com", url = "https://www.sandipsamag.com"), license = @License(name = "Apache 2.0", url = "https://www.sandipsamag.com/license")), externalDocs = @ExternalDocumentation(description = "Employee Service Documentation", url = "https://www.javaguides.net/user_management.html"))
public class EmployeeServiceApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
