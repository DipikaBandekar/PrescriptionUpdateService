package com.pillpack.prescription.update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PrescriptionUpdateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrescriptionUpdateServiceApplication.class, args);
	}
	
	/**
	 * create swagger-ui bean
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
	
	/**
	 * create rest template bean
	 * 
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplateForExternal(RestTemplateBuilder builder) {
		return builder.build();
	}
}
