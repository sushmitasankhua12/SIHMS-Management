import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacaultiDashbordComponent } from './facaulti-dashbord.component';

describe('FacaultiDashbordComponent', () => {
  let component: FacaultiDashbordComponent;
  let fixture: ComponentFixture<FacaultiDashbordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FacaultiDashbordComponent]
    });
    fixture = TestBed.createComponent(FacaultiDashbordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
