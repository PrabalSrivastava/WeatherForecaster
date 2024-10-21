package com.ps.weather_forecaster_backend.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherData {

    private long dt;

    private Main main;

    private List<Weather> weather;

    private Clouds clouds;

    private Wind wind;

    private int visibility;

    private double pop;

    private Rain rain;

    private Sys sys;

    private String dt_txt;

    public String getDt_txt() {
        return dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    @Getter @Setter
    public static class Main {

        private double temp;

        private double feels_like;

        private double temp_min;

        private double temp_max;

        private int pressure;

        private int humidity;

        private int sea_level;

        private int grnd_level;

        private int temp_kf;

        public double getTemp_min() {
            return temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

    }

    @Getter @Setter
    public static class Weather {
        private int id;

        private String main;

        private String description;

        private String icon;

        public String getMain() {
            return main;
        }

    }

    @Getter @Setter
    public static class Clouds {

        private int all;

    }

    @Getter @Setter
    public static class Wind {
        
        private double speed;

        private int deg;

        private double gust;

        public double getSpeed() {
            return speed;
        }

    }

    @Getter @Setter
    public static class Rain {

        @JsonProperty("3h")
        private double _3h;
    
    }

    @Getter @Setter
    public static class Sys {
        
        private String pod;
    
    }
}
