import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneratorMatchesComponent } from './generator-matches.component';

describe('GeneratorMatchesComponent', () => {
  let component: GeneratorMatchesComponent;
  let fixture: ComponentFixture<GeneratorMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GeneratorMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneratorMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
