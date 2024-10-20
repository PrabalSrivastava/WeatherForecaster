package com.ps.weather_forecaster_backend.controller;

import com.ps.weather_forecaster_backend.exception.WeatherForecastGenericException;
import com.ps.weather_forecaster_backend.model.WeatherForecastResponse;
import com.ps.weather_forecaster_backend.service.WeatherForecastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1")
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @Operation(summary = "Get 3-day weather forecast for a city",
            description = "Returns the high and low temperatures for the next 3 days along with any recommendation for the city.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of weather forecast"),
            @ApiResponse(responseCode = "404", description = "City not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/forecast")
    public ResponseEntity<WeatherForecastResponse> getWeatherForecastByCity(@RequestParam("city") String cityName) {
        return ResponseEntity.ok(weatherForecastService.getWeatherForecast(cityName));
    }

}
