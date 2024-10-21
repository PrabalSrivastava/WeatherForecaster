package com.ps.weather_forecaster_backend.client.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.time.format.DateTimeFormatter;

@Configuration
public class OpenWeatherClientConfig {

    @Bean
    @Profile("dev")
    public String appKeyDev(@Value("${openweather.api.key}") String apiKey) {
        return apiKey;
    }

    @Bean
    @Profile("prod")
    public String appKeyProd() {
        return System.getenv("OPEN_WEATHER_API_KEY");
    }

    @Value("${openweather.api.baseUrl}")
    private String apiBaseUrl;

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

}
