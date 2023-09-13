import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddApplyOfferComponent } from './add-apply-offer.component';

describe('AddApplyOfferComponent', () => {
  let component: AddApplyOfferComponent;
  let fixture: ComponentFixture<AddApplyOfferComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddApplyOfferComponent]
    });
    fixture = TestBed.createComponent(AddApplyOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
