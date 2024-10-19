package com.ps.weather_forecaster_backend.client.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class OpenWeatherClientConfig {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.baseUrl}")
    private String apiBaseUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

}
