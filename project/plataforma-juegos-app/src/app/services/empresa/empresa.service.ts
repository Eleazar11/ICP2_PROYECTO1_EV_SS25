import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private readonly apiUrl: string;

  constructor(
    private http: HttpClient,
    private restConstants: RestConstants
  ) {
    this.apiUrl = `${this.restConstants.getApiURL()}/empresas`;
  }

  /**
   * Registra una empresa junto con su usuario dueño
   */
  registrarEmpresa(data: {
    empresa: {
      nombreEmpresa: string;
      descripcion: string;
    };
    usuario: {
      correo: string;
      contrasena: string;
      nombreCompleto: string;
      fechaNacimiento: string;
    };
  }): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/registro`,
      data
    );
  }
}
