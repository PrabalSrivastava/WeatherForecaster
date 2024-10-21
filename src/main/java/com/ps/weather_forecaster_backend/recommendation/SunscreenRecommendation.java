package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.recommendation.entity.WeatherRecommendationInput;

public class SunscreenRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(WeatherRecommendationInput input) {
        if (input.getTemperature() > 25) {
            return "Use sunscreen lotion.";
        }
        return "";
    }
}
