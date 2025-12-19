import { CanActivateFn, ActivatedRouteSnapshot, Router } from '@angular/router';
import { inject } from '@angular/core';
import { UsuariosService } from '../services/usuarios/usuarios.service';

export const rolGuard: CanActivateFn = (route: ActivatedRouteSnapshot) => {
  const usuariosService = inject(UsuariosService);
  const router = inject(Router);

  const rolEsperado = route.data['rol'];

  if (!usuariosService.tienePermiso(rolEsperado)) {
    router.navigate(['/login']);
    return false;
  }

  return true;
};