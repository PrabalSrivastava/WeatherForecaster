package com.ps.weather_forecaster_backend.exception;

public class WeatherForecastGenericException extends RuntimeException {

    public WeatherForecastGenericException(String message) {
        super(message);
    }

    public WeatherForecastGenericException(String message, Throwable cause) {
        super(message, cause);
    }
}