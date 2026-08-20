import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignhomeworkComponent } from './asignhomework.component';

describe('AsignhomeworkComponent', () => {
  let component: AsignhomeworkComponent;
  let fixture: ComponentFixture<AsignhomeworkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AsignhomeworkComponent]
    });
    fixture = TestBed.createComponent(AsignhomeworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
