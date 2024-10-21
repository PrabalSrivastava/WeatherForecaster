package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;

import java.util.List;

public interface WeatherRecommendationStrategy {
    String generateRecommendation(double temperature, double windSpeed, List<Weather> weathers);
}
