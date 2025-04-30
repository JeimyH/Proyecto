package com.example.Proyecto.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurer {
    @Bean(name= "CustomCorsConfigurer")
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
          @Override
          public void addCorsMappings(CorsRegistry registry){
              registry.addMapping("/api/**")
                      //Permitir solicitudes desde emulador de Android
                      .allowedOrigins("http://10.0.2.2:8080") //Emulador de Android
                      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                      .allowedHeaders("*")
                      .allowCredentials(true);
          }
        };
    }
}
