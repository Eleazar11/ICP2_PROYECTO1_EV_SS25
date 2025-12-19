import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { UsuariosService } from '../services/usuarios/usuarios.service';

export const authGuard: CanActivateFn = () => {
  const usuariosService = inject(UsuariosService);
  const router = inject(Router);

  const token = usuariosService.obtenerToken();

  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  return true;
};