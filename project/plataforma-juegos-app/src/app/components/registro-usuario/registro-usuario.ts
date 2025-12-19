import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { UsuariosService } from '../../services/usuarios/usuarios.service';
import { Usuario } from '../../models/Usuario';
import { TipoUsuario } from '../../models/TipoUsuario';

@Component({
  selector: 'app-registro-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registro-usuario.html',
  styleUrl: './registro-usuario.css',
})
export class RegistroUsuario {

  mensaje: string | null = null;
  errorMensaje: string | null = null;

  constructor(
    private usuariosService: UsuariosService,
    private router: Router
  ) {}

  onRegister(form: any): void {

    if (!form.valid) {
      this.errorMensaje = 'Todos los campos son obligatorios.';
      return;
    }

    const formValue = form.value;

    const usuario: Usuario = {
      nickname: formValue.nickname,
      nombreCompleto: formValue.nombreCompleto,
      correo: formValue.correo,              // inmutable
      contrasena: formValue.contrasena,
      fechaNacimiento: new Date(formValue.fechaNacimiento),
      telefono: formValue.numeroTelefono,
      pais: formValue.pais,

      // Controlados por el sistema
      tipoUsuario: TipoUsuario.GAMER,
      activo: true
    };

    console.log('Usuario a registrar:', usuario);

    this.usuariosService.registrarUsuario(usuario).subscribe({
      next: (response) => {
        this.mensaje = response.mensaje || 'Usuario registrado correctamente';
        this.errorMensaje = null;

        setTimeout(() => {
          this.router.navigate(['/home']);
        }, 2000);
      },
      error: (err) => {
        console.error(err);
        this.errorMensaje = err.error?.error || 'Error al registrar usuario';
        this.mensaje = null;
      }
    });
  }

  navigateTo(ruta: string): void {
    this.router.navigate([ruta]);
  }
}
