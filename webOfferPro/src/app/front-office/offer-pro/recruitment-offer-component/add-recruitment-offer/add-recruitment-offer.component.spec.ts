import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRecruitmentOfferComponent } from './add-recruitment-offer.component';

describe('AddRecruitmentOfferComponent', () => {
  let component: AddRecruitmentOfferComponent;
  let fixture: ComponentFixture<AddRecruitmentOfferComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddRecruitmentOfferComponent]
    });
    fixture = TestBed.createComponent(AddRecruitmentOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
