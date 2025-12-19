import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuariosService } from '../../services/usuarios/usuarios.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {

  loginData = { correo: '', contrasena: '' };
  errorMensaje: string | null = null;

  constructor(private usuariosService: UsuariosService, private router: Router) { }

  onLogin() {
  this.usuariosService.loginUsuario(this.loginData).subscribe({
    next: (resp: any) => {
      localStorage.setItem('token', resp.token);
      localStorage.setItem('rol', resp.rol);

      switch (resp.rol) {
        case 'EMPRESA':
          this.router.navigate(['/empresa']);
          break;
        case 'ADMIN':
          this.router.navigate(['/administrador']);
          break;
        case 'GAMER':
          this.router.navigate(['/gamer']);
          break;
        default:
          this.router.navigate(['/home']);
      }
    },
    error: () => {
      this.errorMensaje = 'Credenciales inválidas o error en el servidor.';
    }
  });
}

  volverHome() {
    this.router.navigate(['/home']);
  }
}
