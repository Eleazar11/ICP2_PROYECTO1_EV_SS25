import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearCategoriaVideojuego } from './crear-categoria-videojuego';

describe('CrearCategoriaVideojuego', () => {
  let component: CrearCategoriaVideojuego;
  let fixture: ComponentFixture<CrearCategoriaVideojuego>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearCategoriaVideojuego]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearCategoriaVideojuego);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
