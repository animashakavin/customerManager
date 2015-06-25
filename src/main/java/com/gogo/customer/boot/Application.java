package com.gogo.customer.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/** Class to run a SpringApplication  */
@SpringBootApplication()
@ComponentScan("com.gogo")
public class Application extends SpringBootServletInitializer {
	
	/**
	 * Configure the application.
	 * @param applicationBuilder
	 * @return SpringApplicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(Application.class);
	}
	
	/**
	 * Method to generate restTemplate that is  
	 * managed by the Spring container.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * Method to generate asyncRestTemplate that is  
	 * managed by the Spring container. 
	 */
	@Bean
	public AsyncRestTemplate asyncRestTemplate() {
		return new AsyncRestTemplate();
	}

	/**
	 * Main method.Entry point to spring boot application. 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class,
				args);
	}
	
	/**
	 * A Sub class for a AbstractJsonpResponseBodyAdvice to instruct the 
	 * MappingJackson2HttpMessageConverter to serialize with JSONP formatting. 
	 * specifies the query parameter callback function name as the name of the 
	 * JSONP callback function.
	 *
	 */
	@ControllerAdvice
	static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
		public JsonpAdvice() {
			super("callback");
		}
	} 
	
	
}
