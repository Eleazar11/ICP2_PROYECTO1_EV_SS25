import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { UsuariosService } from '../../../services/usuarios/usuarios.service';

@Component({
  selector: 'app-index-empresa',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './index-empresa.html',
  styleUrls: ['./index-empresa.css']
})
export class IndexEmpresa {
  constructor(private usuariosService: UsuariosService, private router: Router) {}

  logout() {
    this.usuariosService.logout(this.router);
  }
}
