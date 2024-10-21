package com.ps.weather_forecaster_backend.adapter;

import com.ps.weather_forecaster_backend.client.entity.OpenWeatherData.Weather;
import com.ps.weather_forecaster_backend.client.entity.OpenWeatherResponseEntity;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse.WeatherForecast;
import com.ps.weather_forecaster_backend.handler.WeatherForecastDateHandler;
import com.ps.weather_forecaster_backend.recommendation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WeatherForecastAdapterImpl implements WeatherForecastAdapter {

    private final WeatherForecastDateHandler dateHandler;

    private final WeatherRecommendationContext recommendationContext;


    @Autowired
    public WeatherForecastAdapterImpl(WeatherForecastDateHandler dateHandler, WeatherRecommendationContext recommendationContext) {
        this.dateHandler = dateHandler;
        this.recommendationContext = new WeatherRecommendationContext(Arrays.asList(
                new SunscreenRecommendation(),
                new UmbrellaRecommendation(),
                new WindRecommendation(),
                new ThunderstormRecommendation()
        ));
    }

    @Override
    public List<WeatherForecast> adapt(OpenWeatherResponseEntity openWeatherResponse) {
        return openWeatherResponse.getList().stream()
                .map(data -> {
                    String date = data.getDt_txt();
                    double tempMax = Double.parseDouble(String.format("%.2f", data.getMain().getTemp_max() - 273.15));
                    double tempMin = Double.parseDouble(String.format("%.2f", data.getMain().getTemp_min() - 273.15));
                    String recommendation = recommendationContext.generateRecommendations(new WeatherRecommendationInput(tempMax, data.getWind().getSpeed(), data.getWeather()));

                    return new WeatherForecast(date, tempMax, tempMin, recommendation, this.dateHandler);
                })
                .collect(Collectors.toMap(
                        WeatherForecast::getDate,
                        Function.identity(),
                        (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .sorted(Comparator.comparing(WeatherForecast::getDate))
                .collect(Collectors.toList());
    }

}
