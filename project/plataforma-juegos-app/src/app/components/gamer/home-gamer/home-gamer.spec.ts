import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeGamer } from './home-gamer';

describe('HomeGamer', () => {
  let component: HomeGamer;
  let fixture: ComponentFixture<HomeGamer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeGamer]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeGamer);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
