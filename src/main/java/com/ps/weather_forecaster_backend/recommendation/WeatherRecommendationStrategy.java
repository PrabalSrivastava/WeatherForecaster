package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.recommendation.entity.WeatherRecommendationInput;

public interface WeatherRecommendationStrategy {
    String generateRecommendation(WeatherRecommendationInput weatherRecommendationInput);
}
