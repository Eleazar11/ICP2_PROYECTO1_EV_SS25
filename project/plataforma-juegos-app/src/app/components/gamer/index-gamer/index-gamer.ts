import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { UsuariosService } from '../../../services/usuarios/usuarios.service';

@Component({
  selector: 'app-index-gamer',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './index-gamer.html',
  styleUrls: ['./index-gamer.css']
})
export class IndexGamer {
  constructor(private usuariosService: UsuariosService, private router: Router) {}

  logout() {
    this.usuariosService.logout(this.router);
  }
}
