import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexEmpresa } from './index-empresa';

describe('IndexEmpresa', () => {
  let component: IndexEmpresa;
  let fixture: ComponentFixture<IndexEmpresa>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IndexEmpresa]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IndexEmpresa);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
