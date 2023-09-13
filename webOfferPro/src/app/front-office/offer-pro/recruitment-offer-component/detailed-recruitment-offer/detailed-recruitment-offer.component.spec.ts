import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedRecruitmentOfferComponent } from './detailed-recruitment-offer.component';

describe('DetailedRecruitmentOfferComponent', () => {
  let component: DetailedRecruitmentOfferComponent;
  let fixture: ComponentFixture<DetailedRecruitmentOfferComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailedRecruitmentOfferComponent]
    });
    fixture = TestBed.createComponent(DetailedRecruitmentOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
