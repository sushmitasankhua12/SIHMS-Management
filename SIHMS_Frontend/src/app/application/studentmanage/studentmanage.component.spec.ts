import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentmanageComponent } from './studentmanage.component';

describe('StudentmanageComponent', () => {
  let component: StudentmanageComponent;
  let fixture: ComponentFixture<StudentmanageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentmanageComponent]
    });
    fixture = TestBed.createComponent(StudentmanageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
