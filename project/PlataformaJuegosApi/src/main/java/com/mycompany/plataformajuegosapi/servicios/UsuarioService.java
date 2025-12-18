/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.servicios;

import com.mycompany.plataformajuegosapi.database.UsuarioDB;
import com.mycompany.plataformajuegosapi.models.Rol;
import com.mycompany.plataformajuegosapi.models.Seguridad;
import com.mycompany.plataformajuegosapi.models.Usuario;
import java.sql.SQLException;

/**
 *
 * @author eleaz
 */
public class UsuarioService {

    private final UsuarioDB usuarioDB = new UsuarioDB();
    private final Seguridad seguridad = new Seguridad();

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param usuario Objeto con los datos del usuario (nombre, correo,
     * teléfono, contraseña)
     * @throws SQLException si ocurre un error en la base de datos
     */
    public void registrarUsuario(Usuario usuario) throws SQLException {
        // Validaciones básicas
        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new IllegalArgumentException("Correo obligatorio");
        }

        if (usuario.getContrasena() == null || usuario.getContrasena().isBlank()) {
            throw new IllegalArgumentException("Contraseña obligatoria");
        }

        if (usuario.getRol() == null) {
            usuario.setRol(Rol.GAMER); // rol por defecto
        }

        usuarioDB.registrarUsuario(usuario);
    }

    public Usuario autenticarUsuario(String correo, String contrasenaIngresada) throws SQLException {

        Usuario usuario = usuarioDB.obtenerUsuarioPorCorreo(correo);

        if (usuario == null) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        if (!usuario.isActivo()) {
            throw new IllegalArgumentException("Usuario inactivo");
        }

        boolean coincide = seguridad.verificarContrasena(
                contrasenaIngresada,
                usuario.getContrasena()
        );

        if (!coincide) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        return usuario;
    }

    public void actualizarAvatarUsuario(int idUsuario, String avatarUrl) throws SQLException {
        usuarioDB.actualizarAvatar(idUsuario, avatarUrl);
    }

}
