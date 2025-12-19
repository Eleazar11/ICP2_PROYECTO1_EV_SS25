/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.servicios;

import com.mycompany.plataformajuegosapi.database.CarteraDB;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author eleaz
 */
public class CarteraService {

    private final CarteraDB carteraDB = new CarteraDB();

    public void recargar(int idUsuario, BigDecimal monto) throws SQLException {

        if (idUsuario <= 0) {
            throw new IllegalArgumentException("Usuario inválido");
        }

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }

        carteraDB.recargarCartera(idUsuario, monto, "Recarga manual");
    }
}
