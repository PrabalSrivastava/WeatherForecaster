package com.ps.weather_forecaster_backend.exception;

public class WeatherForecastInvalidDateFormatException extends RuntimeException {

    public WeatherForecastInvalidDateFormatException(String message) {
        super(message);
    }

    public WeatherForecastInvalidDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

