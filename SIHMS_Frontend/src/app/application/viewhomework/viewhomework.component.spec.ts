import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewhomeworkComponent } from './viewhomework.component';

describe('ViewhomeworkComponent', () => {
  let component: ViewhomeworkComponent;
  let fixture: ComponentFixture<ViewhomeworkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewhomeworkComponent]
    });
    fixture = TestBed.createComponent(ViewhomeworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
