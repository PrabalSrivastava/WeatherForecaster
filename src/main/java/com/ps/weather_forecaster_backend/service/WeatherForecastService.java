package com.ps.weather_forecaster_backend.service;

import com.ps.weather_forecaster_backend.adapter.WeatherForecastAdapterImpl;
import com.ps.weather_forecaster_backend.client.api.OpenWeatherClient;
import com.ps.weather_forecaster_backend.client.entity.OpenWeatherResponseEntity;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse;

import com.ps.weather_forecaster_backend.recommendation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class WeatherForecastService {

    private final WeatherForecastAdapterImpl weatherForecastAdapter;

    private final OpenWeatherClient client;

    @Autowired
    public WeatherForecastService(WeatherForecastAdapterImpl weatherForecastAdapter, OpenWeatherClient client) {
        this.weatherForecastAdapter = weatherForecastAdapter;
        this.client = client;
    }

    public WeatherForecastResponse getWeatherForecast(String cityName) {
        OpenWeatherResponseEntity openWeatherResponse = client.getWeatherForecast(cityName);
        return new WeatherForecastResponse(openWeatherResponse.getCod(), cityName, weatherForecastAdapter.adapt(openWeatherResponse), "");

    }

}