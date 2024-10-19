package com.ps.weather_forecaster_backend.client.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OpenWeatherCity {
    private long id;

    private String name;

    private Coord coord;

    private String country;

    private int population;

    private int timezone;

    private long sunrise;

    private long sunset;

    @Getter @Setter
    public class Coord {

        private double lat;

        private double lon;

    }
}
