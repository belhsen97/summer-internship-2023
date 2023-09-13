import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageBoxResponseComponent } from './message-box-response.component';

describe('MessageBoxResponseComponent', () => {
  let component: MessageBoxResponseComponent;
  let fixture: ComponentFixture<MessageBoxResponseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MessageBoxResponseComponent]
    });
    fixture = TestBed.createComponent(MessageBoxResponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
