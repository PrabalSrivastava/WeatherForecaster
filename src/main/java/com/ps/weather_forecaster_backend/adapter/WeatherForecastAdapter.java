package com.ps.weather_forecaster_backend.adapter;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherResponseEntity;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse.WeatherForecast;

import java.util.List;

public interface WeatherForecastAdapter {
    List<WeatherForecast> adapt(OpenWeatherResponseEntity openWeatherResponse);
}
