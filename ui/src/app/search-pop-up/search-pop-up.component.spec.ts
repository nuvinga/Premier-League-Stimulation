import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPopUpComponent } from './search-pop-up.component';

describe('SearchPopUpComponent', () => {
  let component: SearchPopUpComponent;
  let fixture: ComponentFixture<SearchPopUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchPopUpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
