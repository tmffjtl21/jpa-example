package com.osstem.jpa.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile({"local","dev","stage"})
public class SwaggerConfig {

	private static List<ResponseMessage> responseMessages = new ArrayList<>();
	
	static {
		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK").build());
		responseMessages.add(new ResponseMessageBuilder().code(400).message("Bad Request").build());
		responseMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());
		responseMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
		responseMessages.add(new ResponseMessageBuilder().code(404).message("Not Found").build());
		responseMessages.add(new ResponseMessageBuilder().code(500).message("Internal Server Error").responseModel(new ModelRef("Error")).build());
	}
	
	@Bean
	public Docket api2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("jpa-example")
				.apiInfo(metadata())
				
				/**
				 * select() method returns an instance of ApiSelectorBuilder, which
				 * provides a way to control the endpoints exposed by Swagger
				 **/
				.select()
				
				/**
				 * Predicates for selection of RequestHandlers can be configured with
				 * the help of RequestHandlerSelectors and PathSelectors. Using any()
				 * for both will make documentation for your entire API available
				 * through Swagger.
				 **/

				.apis(RequestHandlerSelectors.basePackage("com.osstem.jpa.example.web.rest"))
				.paths(PathSelectors.ant("/**"))
				.build()
				//.pathMapping("/")
				
				/**
				 * Instructing Swagger not to use default response messages.
				 */

	            .useDefaultResponseMessages(false)
	            .globalResponseMessage(RequestMethod.GET, responseMessages)
				;
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("JPA-EXAMPLE API").description("OSSTEM Jpa Example API")
				.contact(new Contact("LEE TAEJIN", "", "tmffjtl21@osstem.com"))
				.version("0.1")
				.build();
	}
}