package com.ps.weather_forecaster_backend.recommendation.entity;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;

import java.util.List;


public class WeatherRecommendationInput {

    double temperature;

    double windSpeed;

    List<Weather> weathers;

    public WeatherRecommendationInput(double temperature, double windSpeed, List<Weather> weathers) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.weathers = weathers;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}