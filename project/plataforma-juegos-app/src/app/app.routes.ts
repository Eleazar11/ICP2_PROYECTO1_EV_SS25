import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home';
import { RegistroUsuario } from './components/registro-usuario/registro-usuario';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'registro', component: RegistroUsuario },
{ path: '', redirectTo: '/home', pathMatch: 'full' }
];
