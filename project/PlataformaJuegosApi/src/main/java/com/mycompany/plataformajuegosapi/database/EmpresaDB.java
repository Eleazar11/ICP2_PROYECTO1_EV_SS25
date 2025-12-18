/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.database;

import com.mycompany.plataformajuegosapi.models.Empresa;
import com.mycompany.plataformajuegosapi.models.Rol;
import com.mycompany.plataformajuegosapi.models.Usuario;
import java.sql.*;

/**
 *
 * @author eleaz
 */
public class EmpresaDB {

    public int crearEmpresa(String nombre, String descripcion, Connection conn) throws SQLException {
        String sql = "INSERT INTO empresa (nombre, descripcion) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("No se pudo crear la empresa");
    }

    private void vincularUsuarioEmpresa(int idUsuario, int idEmpresa, Connection conn) throws SQLException {
        String sql = "INSERT INTO usuario_empresa (id_usuario, id_empresa) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idEmpresa);
            ps.executeUpdate();
        }
    }

    public void registrarEmpresaConUsuario(Empresa empresa, Usuario usuario) throws SQLException {

        Connection conn = null;

        try {
            conn = DataSourceDBSingleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            // 1. Crear empresa
            int idEmpresa = crearEmpresa(
                    empresa.getNombreEmpresa(),
                    empresa.getDescripcion(),
                    conn
            );

            // 2. Crear usuario (MISMA CONEXIÓN)
            usuario.setRol(Rol.EMPRESA);
            UsuarioDB usuarioDB = new UsuarioDB();
            usuarioDB.registrarUsuario(usuario, conn);

            // 3. Relacionar usuario con empresa
            vincularUsuarioEmpresa(usuario.getId_usuario(), idEmpresa, conn);

            conn.commit();

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

}
