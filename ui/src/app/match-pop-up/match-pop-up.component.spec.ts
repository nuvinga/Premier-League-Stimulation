import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchPopUpComponent } from './match-pop-up.component';

describe('MatchPopUpComponent', () => {
  let component: MatchPopUpComponent;
  let fixture: ComponentFixture<MatchPopUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchPopUpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
