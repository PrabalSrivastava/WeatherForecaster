import { ComponentFixture, TestBed } from '@angular/core/testing';
import { WeatherDisplayComponent } from './weather-display.component';
import { WeatherService } from '../services/weather.service';
import { of } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('WeatherDisplayComponent', () => {
  let component: WeatherDisplayComponent;
  let fixture: ComponentFixture<WeatherDisplayComponent>;
  let weatherService: WeatherService;

  beforeEach(async () => {
    const mockWeatherService = jasmine.createSpyObj('WeatherService', ['getWeather']);
    mockWeatherService.getWeather.and.returnValue(of({ temp: 20, condition: 'Cloudy' }));

    await TestBed.configureTestingModule({
      imports: [
        WeatherDisplayComponent, // Import standalone component
        HttpClientTestingModule // Import HttpClientTestingModule for service testing
      ],
      providers: [
        { provide: WeatherService, useValue: mockWeatherService }, // Mock WeatherService
        {
          provide: ActivatedRoute,
          useValue: { queryParams: of({ city: 'London' }) } // Mock ActivatedRoute to simulate query params
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(WeatherDisplayComponent);
    component = fixture.componentInstance;
    weatherService = TestBed.inject(WeatherService);
    fixture.detectChanges();
  });

  it('should create the WeatherDisplayComponent', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch weather data on init based on city query param', () => {
    expect(component.weatherData).toEqual({ temp: 20, condition: 'Cloudy' });
  });

  it('should call weatherService.getWeather with the correct city', () => {
    expect(weatherService.getWeather).toHaveBeenCalledWith('London');
  });
});
