/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.database;

import com.mycompany.plataformajuegosapi.models.ComisionEmpresa;
import com.mycompany.plataformajuegosapi.models.ComisionGlobal;
import java.math.BigDecimal;
import java.sql.*;

/**
 *
 * @author eleaz
 */
public class ComisionDB {
    
    public void crearComisionGlobal(ComisionGlobal comision) throws SQLException {

        String sql = "INSERT INTO comision_global (porcentaje, fecha_inicio, fecha_fin) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, comision.getPorcentaje());
            ps.setTimestamp(2, new Timestamp(comision.getFechaInicio().getTime()));

            if (comision.getFechaFin() != null) {
                ps.setTimestamp(3, new Timestamp(comision.getFechaFin().getTime()));
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }

            ps.executeUpdate();
        }
    }

    public boolean existeComisionEmpresa(int idEmpresa) throws SQLException {

        String sql = "SELECT 1 FROM comision_empresa WHERE id_empresa = ?";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public BigDecimal obtenerComisionGlobalActiva() throws SQLException {

        String sql =
            "SELECT porcentaje FROM comision_global "
          + "WHERE fecha_inicio <= NOW() "
          + "AND (fecha_fin IS NULL OR fecha_fin >= NOW()) "
          + "ORDER BY id_comision DESC "
          + "LIMIT 1";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal("porcentaje");
            }
        }
        return null;
    }


    public void crearComisionEmpresa(ComisionEmpresa comision) throws SQLException {

        String sql =
            "INSERT INTO comision_empresa (id_empresa, porcentaje) "
          + "VALUES (?, ?)";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, comision.getIdEmpresa());
            ps.setBigDecimal(2, comision.getPorcentaje());
            ps.executeUpdate();
        }
    }

    public void actualizarComisionEmpresa(ComisionEmpresa comision) throws SQLException {

        String sql =
            "UPDATE comision_empresa SET porcentaje = ? "
          + "WHERE id_empresa = ?";

        try (Connection conn = DataSourceDBSingleton.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, comision.getPorcentaje());
            ps.setInt(2, comision.getIdEmpresa());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new SQLException("No existe comisión para esta empresa");
            }
        }
    }
}

