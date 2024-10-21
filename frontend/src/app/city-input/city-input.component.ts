import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-city-input',
  templateUrl: './city-input.component.html',
  styleUrls: ['./city-input.component.scss'],
  standalone: true,
  imports: [FormsModule]
})
export class CityInputComponent implements OnInit {
  city: string = '';
  offlineMode: boolean = false; // Initialize offline mode

  constructor(private router: Router) {}

  ngOnInit() {
    // Retrieve the offline mode preference from localStorage, if available
    const storedOfflineMode = localStorage.getItem('offlineMode');
    this.offlineMode = storedOfflineMode === 'true'; // Convert string to boolean
  }

  submit() {
    if (this.city) {
      // Save the offline mode state to localStorage
      localStorage.setItem('offlineMode', this.offlineMode.toString());
      this.router.navigate(['/weather-display'], {
        queryParams: { city: this.city, offline: this.offlineMode }
      });
    } else {
      alert('Please enter a city name.');
    }
  }
}
