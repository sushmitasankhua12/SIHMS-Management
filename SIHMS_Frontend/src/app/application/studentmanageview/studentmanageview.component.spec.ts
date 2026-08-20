import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentmanageviewComponent } from './studentmanageview.component';

describe('StudentmanageviewComponent', () => {
  let component: StudentmanageviewComponent;
  let fixture: ComponentFixture<StudentmanageviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentmanageviewComponent]
    });
    fixture = TestBed.createComponent(StudentmanageviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
