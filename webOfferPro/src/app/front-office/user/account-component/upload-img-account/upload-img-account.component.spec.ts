import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadImgAccountComponent } from './upload-img-account.component';

describe('UploadImgAccountComponent', () => {
  let component: UploadImgAccountComponent;
  let fixture: ComponentFixture<UploadImgAccountComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UploadImgAccountComponent]
    });
    fixture = TestBed.createComponent(UploadImgAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
