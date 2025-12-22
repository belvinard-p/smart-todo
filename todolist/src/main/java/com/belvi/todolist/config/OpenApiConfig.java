package com.belvi.todolist.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public OpenAPI openAPI() {

        String description =
                "REST API for managing a Todo List application.\n\n" +
                "This API allows you to:\n" +
                "- Create a todo\n" +
                "- Update a todo\n" +
                "- Retrieve todos\n" +
                "- Delete a todo\n\n" +
                "No authentication is required (learning project).";

        return new OpenAPI()
                .info(new Info()
                        .title("Todo List API")
                        .description(description)
                        .version("1.0.0"));
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            Server server = new Server();
            server.setUrl("http://localhost:" + serverPort);
            server.setDescription("Local Development Server");
            openApi.addServersItem(server);
        };
    }
}
