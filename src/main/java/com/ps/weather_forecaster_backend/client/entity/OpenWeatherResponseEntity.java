package com.ps.weather_forecaster_backend.client.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OpenWeatherResponseEntity {
    private String cod;

    private int message;

    private int cnt;

    private List<OpenWeatherData> list;

    private OpenWeatherCity city;

    public String getCod() {
        return cod;
    }

    public List<OpenWeatherData> getList() {
        return list;
    }
}
