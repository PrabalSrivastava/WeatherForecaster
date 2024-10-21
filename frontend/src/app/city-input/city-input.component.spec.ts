import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CityInputComponent } from './city-input.component';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';

describe('CityInputComponent', () => {
  let component: CityInputComponent;
  let fixture: ComponentFixture<CityInputComponent>;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CityInputComponent, // Import standalone component here
        RouterTestingModule // Import RouterTestingModule to mock navigation
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CityInputComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create the CityInputComponent', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to /weather-display with the city query param on submit', () => {
    const navigateSpy = spyOn(router, 'navigate').and.stub();
    component.city = 'London';
    component.submit();
    expect(navigateSpy).toHaveBeenCalledWith(['/weather-display'], { queryParams: { city: 'London', offline: false } });
  });

  it('should not navigate if city is empty', () => {
    const navigateSpy = spyOn(router, 'navigate');
    component.city = '';
    component.submit();
    expect(navigateSpy).not.toHaveBeenCalled();
  });
});
