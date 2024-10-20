package com.ps.weather_forecaster_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherForecastFrontendController {

    @GetMapping(value = { "/", "/city-input", "/weather-display"})
    public String forwardToFrontend() {
        return "forward:/index.html";
    }
}
