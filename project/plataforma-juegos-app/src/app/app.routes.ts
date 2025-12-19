import { Routes } from '@angular/router';
import { authGuard } from './guards/auth-guard';
import { rolGuard } from './guards/rol-guard';
import { HomeComponent } from './components/home/home';
import { Login } from './components/login/login';
import { RegistroUsuario } from './components/registro-usuario/registro-usuario';
import { IndexAdministrador } from './components/administrador/index-administrador/index-administrador';
import { IndexEmpresa } from './components/empresa/index-empresa/index-empresa';
import { IndexGamer } from './components/gamer/index-gamer/index-gamer';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'registro', component: RegistroUsuario },
    { path: 'login', component: Login },
      {
    path: 'administrador',
    component: IndexAdministrador,
    canActivate: [authGuard, rolGuard],
    data: { rol: 'ADMIN' }
  },
  {
    path: 'empresa',
    component: IndexEmpresa,
    canActivate: [authGuard, rolGuard],
    data: { rol: 'EMPRESA' }
  },
  {
    path: 'gamer',
    component: IndexGamer,
    canActivate: [authGuard, rolGuard],
    data: { rol: 'GAMER' }
  },
{ path: '', redirectTo: '/home', pathMatch: 'full' }
];
