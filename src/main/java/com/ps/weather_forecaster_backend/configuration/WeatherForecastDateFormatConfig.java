package com.ps.weather_forecaster_backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class WeatherForecastDateFormatConfig {

    @Value("${date.format.input}")
    private String inputDatePattern;

    @Value("${date.format.output}")
    private String outputDatePattern;

    public DateTimeFormatter getInputFormatter() {
        return DateTimeFormatter.ofPattern(inputDatePattern);
    }

    public DateTimeFormatter getOutputFormatter() {
        return DateTimeFormatter.ofPattern(outputDatePattern);
    }

}
