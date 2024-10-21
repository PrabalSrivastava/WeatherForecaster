package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.recommendation.entity.WeatherRecommendationInput;

public class WindRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(WeatherRecommendationInput input) {
        if (input.getWindSpeed() > 5) {
            return "It's too windy, watch out!";
        }
        return "";
    }
}
