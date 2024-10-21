package com.ps.weather_forecaster_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"openweather.api.key=test",
		"openweather.api.baseUrl=https://api.openweathermap.org/data/2.5/forecast",
		"date.format.output=MMMM d, yyyy",
		"date.format.input=yyyy-MM-dd HH:mm:ss"
})
class WeatherForecasterApplicationTests {

	@Test
	void contextLoads() {
	}

}
