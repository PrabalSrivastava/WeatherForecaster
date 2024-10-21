import { Routes } from '@angular/router';
import { CityInputComponent } from './city-input/city-input.component';
import { WeatherDisplayComponent } from './weather-display/weather-display.component';
import { ErrorPageComponent } from './error-page/error-page.component'; // Import the ErrorPageComponent

export const routes: Routes = [
  { path: '', redirectTo: '/city-input', pathMatch: 'full' },
  { path: 'city-input', component: CityInputComponent },
  { path: 'weather-display', component: WeatherDisplayComponent },
  { path: 'error/:message', component: ErrorPageComponent }, // Route for error page
  { path: '**', redirectTo: '/error/404', pathMatch: 'full' }
];
