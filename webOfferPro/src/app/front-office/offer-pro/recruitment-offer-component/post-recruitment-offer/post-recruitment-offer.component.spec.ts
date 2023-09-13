import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostRecruitmentOfferComponent } from './post-recruitment-offer.component';

describe('PostRecruitmentOfferComponent', () => {
  let component: PostRecruitmentOfferComponent;
  let fixture: ComponentFixture<PostRecruitmentOfferComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostRecruitmentOfferComponent]
    });
    fixture = TestBed.createComponent(PostRecruitmentOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
