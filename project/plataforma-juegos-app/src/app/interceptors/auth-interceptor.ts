import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { UsuariosService } from '../services/usuarios/usuarios.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const usuariosService = inject(UsuariosService);
  const token = usuariosService.obtenerToken();

  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(req);
};
