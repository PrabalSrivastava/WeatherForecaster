package com.ps.weather_forecaster_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WeatherForecasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecasterApplication.class, args);
	}

}
