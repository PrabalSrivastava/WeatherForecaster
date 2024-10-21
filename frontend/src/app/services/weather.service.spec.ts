import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WeatherService } from './weather.service';

describe('WeatherService', () => {
  let service: WeatherService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [WeatherService],
    });

    service = TestBed.inject(WeatherService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch weather data from the API', () => {
    const mockWeatherData = { temp: 25, condition: 'Sunny' };
    service.getWeather('London', false).subscribe((data) => {
      expect(data).toEqual(mockWeatherData);
    });

    const req = httpMock.expectOne('http://localhost:8080/v1/forecast?city=London');
    expect(req.request.method).toBe('GET');
    req.flush(mockWeatherData);
  });

  afterEach(() => {
    httpMock.verify();
  });
});
