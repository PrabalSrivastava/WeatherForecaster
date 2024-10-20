import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-city-input',
  templateUrl: './city-input.component.html',
  styleUrls: ['./city-input.component.scss'],
  standalone: true,
  imports: [FormsModule]
})
export class CityInputComponent {
  city: string = '';

  constructor(private router: Router) {}

  submit() {
    if (this.city) {
      this.router.navigate(['/weather-display'], { queryParams: { city: this.city } });
    }
  }
}
