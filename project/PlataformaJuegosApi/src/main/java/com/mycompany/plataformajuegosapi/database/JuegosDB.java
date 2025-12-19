/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.database;

import com.mycompany.plataformajuegosapi.models.Juego;
import com.mycompany.plataformajuegosapi.models.JuegoMultimedia;
import java.sql.*;
import java.util.List;

/**
 *
 * @author eleaz
 */
public class JuegosDB {

    public void registrarJuego(Juego juego) throws SQLException {

        String sqlJuego
                = "INSERT INTO juego "
                + "(id_empresa, titulo, descripcion, precio, requisitos_minimos, fecha_lanzamiento, activo, id_clasificacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, 1, ?)";

        String sqlMultimedia
                = "INSERT INTO juego_multimedia (id_juego, url, tipo, orden) "
                + "VALUES (?, ?, ?, ?)";

        Connection conn = null;

        try {
            conn = DataSourceDBSingleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            // INSERT JUEGO
            try (PreparedStatement ps = conn.prepareStatement(sqlJuego, Statement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, juego.getIdEmpresa());
                ps.setString(2, juego.getTitulo());
                ps.setString(3, juego.getDescripcion());
                ps.setBigDecimal(4, juego.getPrecio());
                ps.setString(5, juego.getRequisitosMinimos());

                if (juego.getFechaLanzamiento() != null) {
                    ps.setDate(6, new java.sql.Date(juego.getFechaLanzamiento().getTime()));
                } else {
                    ps.setNull(6, Types.DATE);
                }

                ps.setInt(7, juego.getIdClasificacion());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new SQLException("No se pudo obtener el ID del juego");
                    }
                    juego.setIdJuego(rs.getInt(1));
                }
            }

            // INSERT MULTIMEDIA
            if (juego.getMultimedia() != null && !juego.getMultimedia().isEmpty()) {
                try (PreparedStatement ps = conn.prepareStatement(sqlMultimedia)) {
                    for (JuegoMultimedia m : juego.getMultimedia()) {

                        ps.setInt(1, juego.getIdJuego());
                        ps.setString(2, m.getUrl());
                        ps.setString(3, m.getTipo().toUpperCase());

                        if (m.getOrden() != null) {
                            ps.setInt(4, m.getOrden());
                        } else {
                            ps.setNull(4, Types.INTEGER);
                        }

                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
            }

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

    public void actualizarJuego(Juego juego) throws SQLException {

        String sql
                = "UPDATE juego SET "
                + "titulo = ?, "
                + "descripcion = ?, "
                + "precio = ?, "
                + "requisitos_minimos = ?, "
                + "fecha_lanzamiento = ? "
                + "WHERE id_juego = ?";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, juego.getTitulo());
            ps.setString(2, juego.getDescripcion());
            ps.setBigDecimal(3, juego.getPrecio());
            ps.setString(4, juego.getRequisitosMinimos());

            if (juego.getFechaLanzamiento() != null) {
                ps.setDate(5, new java.sql.Date(juego.getFechaLanzamiento().getTime()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.setInt(6, juego.getIdJuego());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new SQLException("No existe un juego con ese ID");
            }
        }
    }

    public void agregarMultimedia(JuegoMultimedia multimedia) throws SQLException {

        String sql
                = "INSERT INTO juego_multimedia (id_juego, url, tipo, orden) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, multimedia.getIdJuego());
            ps.setString(2, multimedia.getUrl());
            ps.setString(3, multimedia.getTipo());
            ps.setObject(4, multimedia.getOrden());

            ps.executeUpdate();
        }
    }

}
