package com.ps.weather_forecaster_backend.exception.model;

import com.ps.weather_forecaster_backend.handler.WeatherForecastDateHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WeatherForecastGenericExceptionResponse {

    private final String status;

    private final String message;

    public WeatherForecastGenericExceptionResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}

