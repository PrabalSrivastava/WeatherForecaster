package com.ps.weather_forecaster_backend.service;

import com.ps.weather_forecaster_backend.adapter.WeatherForecastAdapterImpl;
import com.ps.weather_forecaster_backend.client.api.OpenWeatherClient;
import com.ps.weather_forecaster_backend.client.entity.OpenWeatherResponseEntity;
import com.ps.weather_forecaster_backend.exception.WeatherForecastGenericException;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse.WeatherForecast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherForecastServiceTest {

    @InjectMocks
    private WeatherForecastService weatherForecastService;

    @Mock
    private OpenWeatherClient client;

    @Mock
    private OpenWeatherResponseEntity mockResponseEntity;

    @Mock
    private WeatherForecastAdapterImpl weatherForecastAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherForecast_Success() {
        String cityName = "London";
        WeatherForecastResponse expectedResponse = new WeatherForecastResponse("200", cityName, Collections.emptyList(), "");

        // Mocking the OpenWeatherClient and WeatherForecastAdapterImpl responses
        when(client.getWeatherForecast(cityName)).thenReturn(mockResponseEntity);
        when(mockResponseEntity.getCod()).thenReturn("200");
        when(weatherForecastAdapter.adapt(mockResponseEntity)).thenReturn(Collections.emptyList());
        when(mockResponseEntity.getList()).thenReturn(Collections.emptyList());

        // Invoking the method under test
        WeatherForecastResponse response = weatherForecastService.getWeatherForecast(cityName);

        // Validating the response
        assertEquals(expectedResponse.getStatus(), response.getStatus());
        assertEquals(expectedResponse.getCity(), response.getCity());
        assertEquals(expectedResponse.getForecasts(), response.getForecasts());
    }

    @Test
    public void testGetWeatherForecast_Error() {
        String cityName = "Unknown City";
        when(client.getWeatherForecast(cityName)).thenThrow(new WeatherForecastGenericException("Error: Unknown"));

        Exception exception = assertThrows(WeatherForecastGenericException.class, () -> {
            weatherForecastService.getWeatherForecast(cityName);
        });

        assertEquals("Error: Unknown", exception.getMessage());
    }
}
