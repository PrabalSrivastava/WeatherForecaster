import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WeatherService {
  private apiUrl: string = 'http://localhost:8080/v1/forecast';
  private ttl: number = 3 * 3600 * 1000; // Cache TTL: 3 hour in milliseconds

  constructor(private http: HttpClient) {}

  getWeather(city: string, offlineMode: boolean): Observable<any> {
    const cacheKey = `weatherData_${city}`;
    const cachedData = this.getCachedData(cacheKey);

    if (offlineMode && cachedData) {
      // Return cached data if offline mode is enabled and cache is valid
      return of(cachedData.data);
    } else {
      // Fetch from the backend and cache the data
      return new Observable(observer => {
        this.http.get(`${this.apiUrl}?city=${city}`).subscribe(
          data => {
            this.cacheData(cacheKey, data); // Cache the new data with timestamp
            observer.next(data);
            observer.complete();
          },
          error => {
            observer.error(error);
          }
        );
      });
    }
  }

  // Cache the data with a timestamp
  private cacheData(key: string, data: any) {
    const cacheEntry = {
      data: data,
      timestamp: new Date().getTime()
    };
    localStorage.setItem(key, JSON.stringify(cacheEntry));
  }

  // Retrieve cached data and check TTL expiration
  private getCachedData(key: string): any | null {
    const cacheEntry = localStorage.getItem(key);
    if (!cacheEntry) {
      return null; // No cached data
    }

    const { data, timestamp } = JSON.parse(cacheEntry);
    const currentTime = new Date().getTime();

    if (currentTime - timestamp < this.ttl) {
      // Cache is still valid
      return { data };
    } else {
      // Cache expired
      localStorage.removeItem(key);
      return null;
    }
  }
}
