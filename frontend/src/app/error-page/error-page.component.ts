import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-error-page',
  standalone: true,
  imports: [],
  templateUrl: './error-page.component.html',
  styleUrl: './error-page.component.scss'
})

export class ErrorPageComponent implements OnInit {
  errorMessage: string | null = null;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Get the error message from route parameters or default message
    this.errorMessage = this.route.snapshot.paramMap.get('message') || 'An unexpected error occurred.';
  }
}
