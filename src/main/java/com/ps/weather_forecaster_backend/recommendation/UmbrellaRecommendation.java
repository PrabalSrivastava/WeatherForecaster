package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.recommendation.entity.WeatherRecommendationInput;

public class UmbrellaRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(WeatherRecommendationInput input) {
        if (input.getWeathers().stream().anyMatch(weather -> "Rain".equals(weather.getMain()))) {
            return "Carry umbrella.";
        }
        return "";
    }
}
