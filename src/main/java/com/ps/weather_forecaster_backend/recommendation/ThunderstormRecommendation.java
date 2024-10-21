package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.recommendation.entity.WeatherRecommendationInput;

public class ThunderstormRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(WeatherRecommendationInput input) {
        if (input.getWeathers().stream().anyMatch(weather -> "Thunderstorm".equals(weather.getMain()))) {
            return "Don't step out! A storm is brewing!";
        }
        return "";
    }
}
