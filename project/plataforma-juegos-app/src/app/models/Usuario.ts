import { TipoUsuario } from "./TipoUsuario";

export interface Usuario {
  idUsuario?: number;           
  correo: string;
  contrasena: string;
  nombreCompleto: string;
  fechaNacimiento: Date;
  nickname: string;
  rol: TipoUsuario;
  telefono?: string;
  pais?: string;
  fechaRegistro?: Date;
  activo?: boolean;
}
