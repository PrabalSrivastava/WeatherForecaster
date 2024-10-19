package com.ps.weather_forecaster_backend.service;

import com.ps.weather_forecaster_backend.client.api.OpenWeatherClient;
import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;
import com.ps.weather_forecaster_backend.client.entity.OpenWeatherResponseEntity;
import com.ps.weather_forecaster_backend.handler.WeatherForecastDateHandler;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse.WeatherForecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WeatherForecastService {

    private final WeatherForecastDateHandler dateHandler;

    private final OpenWeatherClient client;

    @Autowired
    public WeatherForecastService(WeatherForecastDateHandler dateHandler, OpenWeatherClient client) {
        this.dateHandler = dateHandler;
        this.client = client;
    }

    public WeatherForecastResponse getWeatherForecast(String cityName) {

        OpenWeatherResponseEntity openWeatherResponse = client.getWeatherForecast(cityName);
        return new WeatherForecastResponse(openWeatherResponse.getCod(), cityName, convertToWeatherForecasts(openWeatherResponse), "");

    }

    private List<WeatherForecast> convertToWeatherForecasts(OpenWeatherResponseEntity openWeatherResponse) {

        return openWeatherResponse.getList().stream().map(data -> {
            String date = data.getDt_txt();
            double tempMax = data.getMain().getTemp_max() - 273.15; // Kelvin to Celsius
            double tempMin = data.getMain().getTemp_min() - 273.15;
            String recommendation = generateRecommendation(tempMax, data.getWind().getSpeed(), data.getWeather()); //TODO: Implement Better Recommendations

            return new WeatherForecast(date, tempMax, tempMin, recommendation, this.dateHandler);
        }).collect(Collectors.toMap(
            WeatherForecast::getDate,
            Function.identity(),
            (existing, replacement) -> existing
        ))
        .values()
        .stream()
        .sorted(Comparator.comparing(WeatherForecast::getDate))
        .collect(Collectors.toList());
    }

    private String generateRecommendation(double temperature, double windSpeed, List<Weather> weathers) {
        StringBuilder recommendation = new StringBuilder();

        if (temperature > 25) {
            recommendation.append("Use sunscreen lotion.");
        }

        if (weathers.stream().filter(weather -> "Rain".equals(weather.getMain())).findFirst().isPresent()) {
            if (recommendation.length() > 0) {
                recommendation.append(" ");
            }
            recommendation.append("Carry umbrella.");
        }

        if (windSpeed > 5) {
            if (recommendation.length() > 0) {
                recommendation.append(" ");
            }
            recommendation.append("It's too windy, watch out!");
        }

        if (weathers.stream().filter(weather -> "Thunderstorm".equals(weather.getMain())).findFirst().isPresent()) {
            if (recommendation.length() > 0) {
                recommendation.append(" ");
            }
            recommendation.append("Don't step out! A storm is brewing!");
        }

        return recommendation.length() > 0 ? recommendation.toString() : "-";
    }
}