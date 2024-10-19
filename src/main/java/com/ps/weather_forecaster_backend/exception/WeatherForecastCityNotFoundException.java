package com.ps.weather_forecaster_backend.exception;

public class WeatherForecastCityNotFoundException extends RuntimeException {

    public WeatherForecastCityNotFoundException(String message) {
        super(message);
    }

    public WeatherForecastCityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
