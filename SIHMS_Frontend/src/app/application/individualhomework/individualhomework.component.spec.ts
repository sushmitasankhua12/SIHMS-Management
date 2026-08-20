import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndividualhomeworkComponent } from './individualhomework.component';

describe('IndividualhomeworkComponent', () => {
  let component: IndividualhomeworkComponent;
  let fixture: ComponentFixture<IndividualhomeworkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IndividualhomeworkComponent]
    });
    fixture = TestBed.createComponent(IndividualhomeworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
