package com.ps.weather_forecaster_backend.controller;

import com.ps.weather_forecaster_backend.exception.WeatherForecastGenericException;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse;
import com.ps.weather_forecaster_backend.service.WeatherForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherForecastControllerTest {

    @InjectMocks
    private WeatherForecastController weatherForecastController;

    @Mock
    private WeatherForecastService weatherForecastService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherForecastByCity_Success() {
        String cityName = "London";
        WeatherForecastResponse mockResponse = new WeatherForecastResponse("200", cityName, null, "");

        when(weatherForecastService.getWeatherForecast(cityName)).thenReturn(mockResponse);

        ResponseEntity<WeatherForecastResponse> response = weatherForecastController.getWeatherForecastByCity(cityName);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
        verify(weatherForecastService).getWeatherForecast(cityName);
    }

    @Test
    public void testGetWeatherForecastByCity_CityNotFound() {
        String cityName = "Unknown City";
        when(weatherForecastService.getWeatherForecast(cityName)).thenThrow(new WeatherForecastGenericException("City not found"));

        Exception exception = assertThrows(WeatherForecastGenericException.class, () -> {
            weatherForecastController.getWeatherForecastByCity(cityName);
        });

        assertEquals("City not found", exception.getMessage());
    }
}

