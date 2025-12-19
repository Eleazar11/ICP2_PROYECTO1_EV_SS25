import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexGamer } from './index-gamer';

describe('IndexGamer', () => {
  let component: IndexGamer;
  let fixture: ComponentFixture<IndexGamer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IndexGamer]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IndexGamer);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
