import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CityInputComponent } from './city-input/city-input.component';
import { WeatherDisplayComponent } from './weather-display/weather-display.component';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { HttpClient, HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { RouterModule } from '@angular/router';
import { routes } from './app.routes';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    CityInputComponent,
    WeatherDisplayComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    CommonModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule {}
