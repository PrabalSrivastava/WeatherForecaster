package com.ps.weather_forecaster_backend.exception.handler;

import com.ps.weather_forecaster_backend.exception.WeatherForecastCityNotFoundException;
import com.ps.weather_forecaster_backend.exception.WeatherForecastInvalidDateFormatException;
import com.ps.weather_forecaster_backend.exception.WeatherForecastGenericException;
import com.ps.weather_forecaster_backend.exception.model.WeatherForecastGenericExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class WeatherForecastGlobalExceptionHandler {

    @ExceptionHandler({WeatherForecastGenericException.class, WeatherForecastInvalidDateFormatException.class})
    public ResponseEntity<WeatherForecastGenericExceptionResponse> handleGenericException(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new WeatherForecastGenericExceptionResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage()));
    }

    @ExceptionHandler(WeatherForecastCityNotFoundException.class)
    public ResponseEntity<WeatherForecastGenericExceptionResponse> handleCityNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new WeatherForecastGenericExceptionResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage()));
    }
}