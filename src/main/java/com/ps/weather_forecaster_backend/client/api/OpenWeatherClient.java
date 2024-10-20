package com.ps.weather_forecaster_backend.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.weather_forecaster_backend.client.config.OpenWeatherClientConfig;
import com.ps.weather_forecaster_backend.client.entity.OpenWeatherResponseEntity;
import com.ps.weather_forecaster_backend.exception.WeatherForecastCityNotFoundException;
import com.ps.weather_forecaster_backend.exception.WeatherForecastGenericException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherClient {

    public static final int DEFAULT_FORECAST_COUNT = 17;

    private final OpenWeatherClientConfig clientConfig;

    private final String apiKey;

    @Autowired
    public OpenWeatherClient(OpenWeatherClientConfig clientConfig, String apiKey) {
        this.clientConfig = clientConfig;
        this.apiKey = apiKey;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    public OpenWeatherResponseEntity getWeatherForecast(String city) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(getUrl(city));
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int status = response.getCode();
                if(status == HttpStatus.SC_OK) {
                    String jsonResponse = EntityUtils.toString(response.getEntity());
                    return objectMapper.readValue(jsonResponse, OpenWeatherResponseEntity.class);
                } else if (status == HttpStatus.SC_NOT_FOUND) {
                    throw new WeatherForecastCityNotFoundException("Error: City not found");
                } else {
                    throw new WeatherForecastGenericException("Error: Unknown");
                }
            } catch (ParseException ex) {
                throw new WeatherForecastGenericException(ex.getMessage());
            }
        } catch (IOException ex) {
            throw new WeatherForecastGenericException(ex.getMessage());
        }
    }

    private URI getUrl(String city) {

        try {
            return new URIBuilder(this.clientConfig.getApiBaseUrl())
                    .addParameter("q", city)
                    .addParameter("appid", this.apiKey)
                    .addParameter("cnt", String.valueOf(DEFAULT_FORECAST_COUNT))
                    .build();
        } catch (URISyntaxException ex) {
            throw new WeatherForecastGenericException(ex.getMessage());
        }
    }
}
