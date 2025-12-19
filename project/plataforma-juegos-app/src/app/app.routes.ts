import { Routes } from '@angular/router';
import { authGuard } from './guards/auth-guard';
import { rolGuard } from './guards/rol-guard';
import { HomeComponent } from './components/home/home';
import { Login } from './components/login/login';
import { RegistroUsuario } from './components/registro-usuario/registro-usuario';
import { RegistroEmpresa } from './components/registro-empresa/registro-empresa';
import { IndexAdministrador } from './components/administrador/index-administrador/index-administrador';
import { IndexEmpresa } from './components/empresa/index-empresa/index-empresa';
import { IndexGamer } from './components/gamer/index-gamer/index-gamer';
import { HomeAdministrador } from './components/administrador/home-administrador/home-administrador';
import { HomeEmpresa } from './components/empresa/home-empresa/home-empresa';
import { HomeGamer } from './components/gamer/home-gamer/home-gamer';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'registro', component: RegistroUsuario },
    { path: 'registro-empresa', component: RegistroEmpresa },
    { path: 'login', component: Login },
    {
    path: 'administrador',
    component: IndexAdministrador,
    canActivate: [authGuard, rolGuard],
    data: { rol: 'ADMIN' },
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' }, // 👈 default
      { path: 'home', component: HomeAdministrador }
      // otras vistas de administrador
    ]
  },
  {
    path: 'empresa',
    component: IndexEmpresa,
    canActivate: [authGuard, rolGuard],
    data: { rol: 'EMPRESA' },
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeEmpresa },
      // otras vistas de empresa
    ]
  },

  {
    path: 'gamer',
    component: IndexGamer,
    canActivate: [authGuard, rolGuard],
    data: { rol: 'GAMER' },
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeGamer },
      // otras vistas de gamer
    ]
  },
{ path: '', redirectTo: '/home', pathMatch: 'full' }
];
