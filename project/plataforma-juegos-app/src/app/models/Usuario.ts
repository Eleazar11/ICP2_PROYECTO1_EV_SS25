import { TipoUsuario } from "./TipoUsuario";

export interface Usuario {
  idUsuario?: number;           
  correo: string;
  contrasena: string;
  nombreCompleto: string;
  fechaNacimiento: Date;
  nickname: string;
  tipoUsuario: TipoUsuario;
  telefono?: string;
  pais?: string;
  fechaRegistro?: Date;
  activo?: boolean;
}
