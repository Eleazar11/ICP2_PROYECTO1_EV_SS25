/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.servicios;

import com.mycompany.plataformajuegosapi.database.EmpresaDB;
import com.mycompany.plataformajuegosapi.database.UsuarioDB;
import com.mycompany.plataformajuegosapi.models.Empresa;
import com.mycompany.plataformajuegosapi.models.Rol;
import com.mycompany.plataformajuegosapi.models.Usuario;
import java.sql.SQLException;


/**
 *
 * @author eleaz
 */
public class EmpresaService {

    private final EmpresaDB empresaDB = new EmpresaDB();
    private final UsuarioDB usuarioDB = new UsuarioDB();

    /**
     * Registra una empresa y crea su usuario dueño (rol EMPRESA)
     */
    public void registrarEmpresaConUsuario(Empresa empresa, Usuario usuario) throws SQLException {

        if (empresa == null || empresa.getNombreEmpresa() == null || empresa.getNombreEmpresa().isBlank()) {
            throw new IllegalArgumentException("Nombre de la empresa obligatorio");
        }

        if (usuario == null || usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new IllegalArgumentException("Correo del usuario obligatorio");
        }

        if (usuario.getContrasena() == null || usuario.getContrasena().isBlank()) {
            throw new IllegalArgumentException("Contraseña obligatoria");
        }

        usuario.setRol(Rol.EMPRESA);

        // Transacción completa
        empresaDB.registrarEmpresaConUsuario(empresa, usuario);
    }
}