import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubsViewComponent } from './clubs-view.component';

describe('ClubsViewComponent', () => {
  let component: ClubsViewComponent;
  let fixture: ComponentFixture<ClubsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClubsViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClubsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
