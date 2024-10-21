package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;

import java.util.List;

public class SunscreenRecommendation implements WeatherRecommendationStrategy {
    @Override
    public String generateRecommendation(double temperature, double windSpeed, List<Weather> weathers) {
        if (temperature > 25) {
            return "Use sunscreen lotion.";
        }
        return "";
    }
}
