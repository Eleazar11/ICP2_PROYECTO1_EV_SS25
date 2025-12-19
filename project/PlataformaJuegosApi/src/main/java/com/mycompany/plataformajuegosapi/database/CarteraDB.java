/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.database;

import java.math.BigDecimal;
import java.sql.*;

/**
 *
 * @author eleaz
 */
public class CarteraDB {

    public void recargarCartera(int idUsuario, BigDecimal monto, String referencia) throws SQLException {

        String sqlUpdateSaldo =
                "UPDATE cartera SET saldo = saldo + ? WHERE id_usuario = ?";

        String sqlMovimiento =
                "INSERT INTO movimiento_cartera (id_usuario, tipo, monto, referencia) " +
                "VALUES (?, 'RECARGA', ?, ?)";

        Connection conn = null;

        try {
            conn = DataSourceDBSingleton.getInstance().getConnection();
            conn.setAutoCommit(false);

            // Actualizar saldo
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateSaldo)) {
                ps.setBigDecimal(1, monto);
                ps.setInt(2, idUsuario);

                int filas = ps.executeUpdate();
                if (filas == 0) {
                    throw new SQLException("La cartera no existe para este usuario");
                }
            }

            // Registrar movimiento
            try (PreparedStatement ps = conn.prepareStatement(sqlMovimiento)) {
                ps.setInt(1, idUsuario);
                ps.setBigDecimal(2, monto);
                ps.setString(3, referencia);
                ps.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
