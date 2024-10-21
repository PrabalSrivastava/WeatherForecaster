package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;

import java.util.List;

public class UmbrellaRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(double temperature, double windSpeed, List<Weather> weathers) {
        if (weathers.stream().anyMatch(weather -> "Rain".equals(weather.getMain()))) {
            return "Carry umbrella.";
        }
        return "";
    }
}
