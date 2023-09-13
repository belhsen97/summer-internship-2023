import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePasswordForgotComponent } from './update-password-forgot.component';

describe('UpdatePasswordForgotComponent', () => {
  let component: UpdatePasswordForgotComponent;
  let fixture: ComponentFixture<UpdatePasswordForgotComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatePasswordForgotComponent]
    });
    fixture = TestBed.createComponent(UpdatePasswordForgotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
