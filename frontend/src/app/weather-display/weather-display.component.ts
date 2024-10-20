import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeatherService } from '../services/weather.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-weather-display',
  templateUrl: './weather-display.component.html',
  styleUrls: ['./weather-display.component.scss'],
  standalone: true,
  imports: [CommonModule]
})
export class WeatherDisplayComponent implements OnInit {
  city: string | undefined = undefined;
  weatherData: any = undefined;

  constructor(private route: ActivatedRoute, private weatherService: WeatherService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.city = params['city'];
      this.fetchWeather();
    });
  }

  fetchWeather() {
    if (this.city) {
      this.weatherService.getWeather(this.city).subscribe(data => {
        this.weatherData = data;
      });
    }
  }
}
