import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { UsuariosService } from '../../../services/usuarios/usuarios.service';

@Component({
  selector: 'app-index-administrador',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './index-administrador.html',
  styleUrl: './index-administrador.css'
})
export class IndexAdministrador {

  constructor(private usuariosService: UsuariosService, private router: Router) {}

  logout() {
    this.usuariosService.logout(this.router);
  }
}
