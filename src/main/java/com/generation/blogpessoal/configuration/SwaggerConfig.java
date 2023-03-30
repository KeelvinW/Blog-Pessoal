package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Projeto Blog Pessoal")
					.description("Projeto Blog Pessoal - Kelvin Souza")
					.version ("v0.0.1")
				.license (new License ()
					.name("Generation Brasil - Kelvin Souza")
					.url("http://brazil.generation.org/"))
				.contact(new Contact()
					.name("Kelvin Souza ")
					.url("https://github.com/KeelvinW")
					.email("keelvin.w@hotmail.com")))
				.externalDocs(new ExternalDocumentation()	
					.description("LinkedIn - Kelvin Souza")
					.url("https://www.linkedin.com/in/kelvin-souza-/"));
	}
	
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				
				ApiResponses apiResponse = operation.getResponses();
				
				apiResponse.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponse.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponse.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponse.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponse.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponse.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponse.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
				
			}));
		};
	}

	private ApiResponse createApiResponse(String message) {
		
		return new ApiResponse().description(message);
	}
}
