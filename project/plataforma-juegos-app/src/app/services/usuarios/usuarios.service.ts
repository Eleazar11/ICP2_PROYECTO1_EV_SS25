import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private apiUrl: string;

  constructor(
    private http: HttpClient,
    private restConstants: RestConstants
  ) {
    // URL base del backend
    this.apiUrl = `${this.restConstants.getApiURL()}/usuarios`;
  }

  // Registrar usuario (NO guarda token)
  registrarUsuario(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/registro`, usuario)
      .pipe(catchError(this.manejarError));
  }

  // Login de usuario (GUARDA token)
  loginUsuario(loginData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginData).pipe(
      map(resp => {
        if (!resp?.token) {
          throw new Error('Token no recibido');
        }
        this.guardarToken(resp.token);
        return resp;
      }),
      catchError(this.manejarError)
    );
  }

  // Guardar token
  guardarToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Obtener token
  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  // Eliminar token
  eliminarToken(): void {
    localStorage.removeItem('token');
  }

  // Verificar rol
  tienePermiso(rol: string): boolean {
    const token = this.obtenerToken();
    if (!token) return false;

    try {
      const decoded: any = jwtDecode(token);
      return decoded.rol === rol;
    } catch {
      return false;
    }
  }

  // Roles
  permisosAdministrador(): boolean {
    return this.tienePermiso('ADMINISTRADOR');
  }

  permisosEmpresa(): boolean {
    return this.tienePermiso('EMPRESA');
  }

  permisosGamer(): boolean {
    return this.tienePermiso('GAMER');
  }

  // Manejo de errores
  private manejarError(error: any) {
    console.error('Error:', error);
    return throwError(() => error);
  }
}
