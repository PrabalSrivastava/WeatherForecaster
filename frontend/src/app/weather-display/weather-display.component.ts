import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeatherService } from '../services/weather.service';
import { CommonModule } from '@angular/common';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Router } from '@angular/router';

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

  constructor(private route: ActivatedRoute, private weatherService: WeatherService, private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (!params['city']) this.router.navigate(['/error', 'City field empty!']);
      this.city = params['city'];
      this.fetchWeather();
    });
  }

  fetchWeather() {
    if (this.city) {
      this.weatherService.getWeather(this.city).pipe(
         catchError(error => {
           // Redirect to error page with error message
           this.router.navigate(['/error', error.error.message || 'An unexpected error occurred.']);
           return of(null); // Return a null observable
         })
       ).subscribe(data => {
        this.weatherData = data;
      });
    }
  }
}
