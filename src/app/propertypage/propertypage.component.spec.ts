import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertypageComponent } from './propertypage.component';

describe('PropertypageComponent', () => {
  let component: PropertypageComponent;
  let fixture: ComponentFixture<PropertypageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PropertypageComponent]
    });
    fixture = TestBed.createComponent(PropertypageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
