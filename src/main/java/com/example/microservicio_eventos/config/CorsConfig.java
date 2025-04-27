package com.example.microservicio_eventos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration // Marca esta clase como de configuración, Spring la carga al arrancar
public class CorsConfig {

    @Bean // Registra este configurador como un Bean que Spring puede utilizar
    public WebMvcConfigurer corsConfigurer() {
        // Devuelve una implementación de WebMvcConfigurer
        // que va a personalizar las reglas de CORS

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
                
            }
        };
    }
}

