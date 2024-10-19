package com.ps.weather_forecaster_backend.handler;

import com.ps.weather_forecaster_backend.configuration.WeatherForecastDateFormatConfig;
import com.ps.weather_forecaster_backend.exception.WeatherForecastInvalidDateFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class WeatherForecastDateHandler {

    private final WeatherForecastDateFormatConfig dateFormatConfig;

    @Autowired
    public WeatherForecastDateHandler(WeatherForecastDateFormatConfig dateFormatConfig) {
        this.dateFormatConfig = dateFormatConfig;
    }

    public LocalDate parseInputDate(String dateString) {
        try {
            return LocalDate.parse(dateString, dateFormatConfig.getInputFormatter());
        } catch (DateTimeParseException e) {
            throw new WeatherForecastInvalidDateFormatException("Error parsing date string: " + dateString);
        }
    }

    public String formatOutputDate(LocalDate date) {
        try {
            return date.format(dateFormatConfig.getOutputFormatter());
        } catch (DateTimeParseException e) {
            throw new WeatherForecastInvalidDateFormatException("Error formatting date: " + date);
        }
    }
}