import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchRecruitmentOfferComponent } from './search-recruitment-offer.component';

describe('SearchRecruitmentOfferComponent', () => {
  let component: SearchRecruitmentOfferComponent;
  let fixture: ComponentFixture<SearchRecruitmentOfferComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchRecruitmentOfferComponent]
    });
    fixture = TestBed.createComponent(SearchRecruitmentOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
