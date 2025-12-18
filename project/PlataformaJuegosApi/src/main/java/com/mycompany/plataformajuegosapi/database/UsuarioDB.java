/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.database;

import com.mycompany.plataformajuegosapi.models.Rol;
import com.mycompany.plataformajuegosapi.models.Seguridad;
import com.mycompany.plataformajuegosapi.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author eleaz
 */
public class UsuarioDB {

    private final Seguridad seguridad = new Seguridad();

    /**
     * Registra un usuario y, si el rol es GAMER, crea su cartera digital. TODO
     * se ejecuta dentro de UNA transacción.
     */
    public boolean registrarUsuario(Usuario usuario) throws SQLException {

        String sqlUsuario
                = "INSERT INTO usuario "
                + "(correo, password_hash, nombre_completo, fecha_nacimiento, nickname, telefono, pais, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";

        String sqlUsuarioRol
                = "INSERT INTO usuario_rol (id_usuario, id_rol) "
                + "VALUES (?, ?)";

        String sqlCartera
                = "INSERT INTO cartera (id_usuario, saldo) "
                + "VALUES (?, 0.00)";

        Connection conn = null;

        try {
            conn = DataSourceDBSingleton.getInstance().getConnection();
            conn.setAutoCommit(false); // INICIO TRANSACCIÓN

            // 1. Insertar usuario
            try (PreparedStatement ps = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, usuario.getCorreo());
                ps.setString(2, seguridad.encriptarContrasena(usuario.getContrasena()));
                ps.setString(3, usuario.getNombre_completo());
                ps.setDate(4, new java.sql.Date(usuario.getFecha_nacimiento().getTime()));
                ps.setString(5, usuario.getNickname());
                ps.setString(6, usuario.getTelefono());
                ps.setString(7, usuario.getPais());

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new SQLException("No se pudo obtener el ID del usuario");
                    }
                    usuario.setId_usuario(rs.getInt(1));
                }
            }

            // 2. Asignar rol
            try (PreparedStatement ps = conn.prepareStatement(sqlUsuarioRol)) {
                ps.setInt(1, usuario.getId_usuario());
                ps.setInt(2, obtenerIdRol(usuario.getRol(), conn));
                ps.executeUpdate();
            }

            // 3. Crear cartera SOLO si es GAMER
            if (usuario.getRol() == Rol.GAMER) {
                try (PreparedStatement ps = conn.prepareStatement(sqlCartera)) {
                    ps.setInt(1, usuario.getId_usuario());
                    ps.executeUpdate();
                }
            }

            conn.commit(); // FIN TRANSACCIÓN
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    /**
     * Obtiene el ID del rol desde la tabla rol
     */
    private int obtenerIdRol(Rol rol, Connection conn) throws SQLException {
        String sql = "SELECT id_rol FROM rol WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rol.name());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_rol");
                }
            }
        }
        throw new SQLException("Rol no encontrado: " + rol.name());
    }

    public void actualizarAvatar(int idUsuario, String avatarUrl) throws SQLException {

        String sql = "UPDATE usuario SET avatar_url = ? WHERE id_usuario = ?";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, avatarUrl);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        }
    }

    //para empresa
    public void registrarUsuario(Usuario usuario, Connection conn) throws SQLException {

        String sqlUsuario
                = "INSERT INTO usuario "
                + "(correo, password_hash, nombre_completo, fecha_nacimiento, nickname, telefono, pais, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";

        String sqlUsuarioRol
                = "INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getCorreo());
            ps.setString(2, seguridad.encriptarContrasena(usuario.getContrasena()));
            ps.setString(3, usuario.getNombre_completo());
            ps.setDate(4, new java.sql.Date(usuario.getFecha_nacimiento().getTime()));
            ps.setString(5, usuario.getNickname());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getPais());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("No se pudo obtener ID del usuario");
                }
                usuario.setId_usuario(rs.getInt(1));
            }
        }

        try (PreparedStatement ps = conn.prepareStatement(sqlUsuarioRol)) {
            ps.setInt(1, usuario.getId_usuario());
            ps.setInt(2, obtenerIdRol(usuario.getRol(), conn));
            ps.executeUpdate();
        }
    }

}
