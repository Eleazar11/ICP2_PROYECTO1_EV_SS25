import { Component } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { EmpresaService } from '../../services/empresa/empresa.service';

@Component({
  selector: 'app-registro-empresa',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './registro-empresa.html',
  styleUrl: './registro-empresa.css'
})
export class RegistroEmpresa {
  mensaje: string | null = null;
  errorMensaje: string | null = null;

  form = {
    empresa: {
      nombreEmpresa: '',
      descripcion: ''
    },
    usuario: {
      correo: '',
      contrasena: '',
      nombreCompleto: '',
      fechaNacimiento: ''
    }
  };

  constructor(
    private empresaService: EmpresaService,
    private router: Router
  ) {}

  onRegister(form: NgForm) {
    if (form.invalid) return;

    this.mensaje = null;
    this.errorMensaje = null;

    this.empresaService.registrarEmpresa(this.form).subscribe({
      next: () => {
        this.mensaje = 'Empresa registrada correctamente';
        form.resetForm();
      },
      error: () => {
        this.errorMensaje = 'Error al registrar la empresa';
      }
    });
  }

  volverHome() {
    this.router.navigate(['/home']);
  }
}
