import { Routes } from '@angular/router';
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
    { path: 'administrador', component: IndexAdministrador },
    { path: 'empresa', component: IndexEmpresa },
    { path: 'gamer', component: IndexGamer },
{ path: '', redirectTo: '/home', pathMatch: 'full' }
];
