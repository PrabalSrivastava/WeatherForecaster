package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;

import java.util.List;

public class ThunderstormRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(double temperature, double windSpeed, List<Weather> weathers) {
        if (weathers.stream().anyMatch(weather -> "Thunderstorm".equals(weather.getMain()))) {
            return "Don't step out! A storm is brewing!";
        }
        return "";
    }
}
