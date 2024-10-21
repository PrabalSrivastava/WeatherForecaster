import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeatherService } from '../services/weather.service';
import { CommonModule } from '@angular/common';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

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
  errorMessage: string | undefined = undefined;

  constructor(private route: ActivatedRoute, private weatherService: WeatherService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.city = params['city'];
      this.fetchWeather();
    });
  }

  fetchWeather() {
    if (this.city) {
      this.weatherService.getWeather(this.city).pipe(
         catchError(error => {
           this.errorMessage = error.error.message || 'An unexpected error occurred. Please try again.';
           return of(null); // Return a null observable
         })
       ).subscribe(data => {
        this.weatherData = data;
      });
    }
  }
}
