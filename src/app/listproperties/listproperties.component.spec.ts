import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListpropertiesComponent } from './listproperties.component';

describe('ListpropertiesComponent', () => {
  let component: ListpropertiesComponent;
  let fixture: ComponentFixture<ListpropertiesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListpropertiesComponent]
    });
    fixture = TestBed.createComponent(ListpropertiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
