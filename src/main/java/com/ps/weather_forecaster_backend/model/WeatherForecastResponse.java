package com.ps.weather_forecaster_backend.model;

import com.ps.weather_forecaster_backend.handler.WeatherForecastDateHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WeatherForecastResponse {

    private String status;

    private String city;

    private List<WeatherForecast> forecasts;

    private String message;

    public WeatherForecastResponse(String status, String city, List<WeatherForecast> forecasts, String message) {
        this.status = status;
        this.city = city;
        this.forecasts = forecasts;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<WeatherForecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<WeatherForecast> forecasts) {
        this.forecasts = forecasts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class WeatherForecast{

        private String date;

        private double highTemp;

        private double lowTemp;

        private String recommendation;

        private final WeatherForecastDateHandler dateHandler;

        @Autowired
        public WeatherForecast(WeatherForecastDateHandler dateHandler) {
            this.dateHandler = dateHandler;
        }

        public WeatherForecast(String dateString, double highTemp, double lowTemp, String recommendation, WeatherForecastDateHandler dateHandler) {
            this.dateHandler = dateHandler;
            this.date = dateHandler.formatOutputDate(dateHandler.parseInputDate(dateString));
            this.highTemp = highTemp;
            this.lowTemp = lowTemp;
            this.recommendation = recommendation;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getHighTemp() {
            return highTemp;
        }

        public void setHighTemp(double highTemp) {
            this.highTemp = highTemp;
        }

        public double getLowTemp() {
            return lowTemp;
        }

        public void setLowTemp(double lowTemp) {
            this.lowTemp = lowTemp;
        }

        public String getRecommendation() {
            return recommendation;
        }

        public void setRecommendation(String recommendation) {
            this.recommendation = recommendation;
        }

    }
}

