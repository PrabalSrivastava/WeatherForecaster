package com.ps.weather_forecaster_backend.recommendation;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherRecommendationContext {

    private final List<WeatherRecommendationStrategy> strategies;

    public WeatherRecommendationContext(List<WeatherRecommendationStrategy> strategies) {
        this.strategies = strategies;
    }

    public String generateRecommendations(double temperature, double windSpeed, List<Weather> weathers) {
        StringBuilder recommendations = new StringBuilder();

        for (WeatherRecommendationStrategy strategy : strategies) {
            String recommendation = strategy.generateRecommendation(temperature, windSpeed, weathers);
            if (!recommendation.isEmpty()) {
                if (!recommendations.isEmpty()) {
                    recommendations.append(" ");
                }
                recommendations.append(recommendation);
            }
        }

        return !recommendations.isEmpty() ? recommendations.toString() : "Looks like a good day!";
    }
}
