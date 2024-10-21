package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;

import java.util.List;

public class WindRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(WeatherRecommendationInput input) {
        if (input.getWindSpeed() > 5) {
            return "It's too windy, watch out!";
        }
        return "";
    }
}
