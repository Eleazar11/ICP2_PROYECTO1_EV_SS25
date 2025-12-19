/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.database;

import com.mycompany.plataformajuegosapi.models.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eleaz
 */
public class CategoriaDB {

    public void crearCategoria(Categoria categoria) throws SQLException {

        String sql = "INSERT INTO categoria (nombre, activa) VALUES (?, 1)";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, categoria.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setIdCategoria(rs.getInt(1));
                }
            }
        }
    }

    public List<Categoria> obtenerTodas() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT id_categoria, nombre, activa FROM categoria";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setActiva(rs.getBoolean("activa"));

                categorias.add(categoria);
            }
        }

        return categorias;
    }

    public void sugerirCategoria(Categoria categoria) throws SQLException {

        String sql = "INSERT INTO categoria (nombre, activa) VALUES (?, 0)";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, categoria.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setIdCategoria(rs.getInt(1));
                }
            }
        }
    }

    public List<Categoria> obtenerActivas() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT id_categoria, nombre, activa FROM categoria WHERE activa = 1";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setActiva(rs.getBoolean("activa"));
                categorias.add(categoria);
            }
        }

        return categorias;
    }

    public List<Categoria> obtenerNoActivas() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT id_categoria, nombre, activa FROM categoria WHERE activa = 0";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setActiva(rs.getBoolean("activa"));
                categorias.add(categoria);
            }
        }

        return categorias;
    }

    public void actualizarEstado(int idCategoria, boolean activa) throws SQLException {

        String sql = "UPDATE categoria SET activa = ? WHERE id_categoria = ?";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, activa);
            ps.setInt(2, idCategoria);

            ps.executeUpdate();
        }
    }

}
