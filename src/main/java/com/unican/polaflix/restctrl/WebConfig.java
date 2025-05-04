package com.unican.polaflix.restctrl;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas las rutas
                .allowedOrigins("http://127.0.0.1:3001", "http://127.0.0.1:8080") // Tus orígenes permitidos
                .allowedMethods("*") // Métodos HTTP permitidos (GET, POST, etc.)
                .allowedHeaders("*") // Cabeceras permitidas
                .allowCredentials(true); // Si necesitas enviar cookies o autenticación
    }
}
