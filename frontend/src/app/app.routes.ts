import { Routes } from '@angular/router';
import { CityInputComponent } from './city-input/city-input.component';
import { WeatherDisplayComponent } from './weather-display/weather-display.component';

export const routes: Routes = [
  { path: '', redirectTo: '/city-input', pathMatch: 'full' },
  { path: 'city-input', component: CityInputComponent },
  { path: 'weather-display', component: WeatherDisplayComponent },
  { path: '**', redirectTo: '/city-input' } // wildcard route for a 404 page
];
