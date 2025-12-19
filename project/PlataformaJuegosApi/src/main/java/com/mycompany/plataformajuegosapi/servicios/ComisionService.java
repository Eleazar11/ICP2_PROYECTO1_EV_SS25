/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.servicios;

import com.mycompany.plataformajuegosapi.database.ComisionDB;
import com.mycompany.plataformajuegosapi.models.ComisionEmpresa;
import com.mycompany.plataformajuegosapi.models.ComisionGlobal;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author eleaz
 */
public class ComisionService {

    private static final BigDecimal MAX_PORCENTAJE = BigDecimal.valueOf(25);

    private final ComisionDB comisionDB = new ComisionDB();

    public void crearComisionGlobal(ComisionGlobal comision) throws SQLException {

        validarPorcentaje(comision.getPorcentaje());

        if (comision.getFechaInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        }

        if (comision.getFechaFin() != null
                && comision.getFechaFin().before(comision.getFechaInicio())) {
            throw new IllegalArgumentException(
                    "La fecha fin no puede ser anterior a la fecha inicio"
            );
        }

        comisionDB.crearComisionGlobal(comision);
    }

    public void crearComisionEmpresa(ComisionEmpresa comision) throws SQLException {

        validarEmpresa(comision);
        validarPorcentaje(comision.getPorcentaje());

        if (comisionDB.existeComisionEmpresa(comision.getIdEmpresa())) {
            throw new IllegalArgumentException(
                    "Esta empresa ya tiene una comisión asignada. "
                    + "Use la opción de modificar comisión."
            );
        }

        BigDecimal global = comisionDB.obtenerComisionGlobalActiva();

        if (global != null && comision.getPorcentaje().compareTo(global) > 0) {
            throw new IllegalArgumentException(
                    "La comisión de la empresa no puede superar la comisión global (" + global + "%)"
            );
        }

        comisionDB.crearComisionEmpresa(comision);
    }

    public void actualizarComisionEmpresa(ComisionEmpresa comision) throws SQLException {

        validarEmpresa(comision);
        validarPorcentaje(comision.getPorcentaje());

        BigDecimal global = comisionDB.obtenerComisionGlobalActiva();

        if (global != null && comision.getPorcentaje().compareTo(global) > 0) {
            throw new IllegalArgumentException(
                    "La comisión de la empresa no puede superar la comisión global (" + global + "%)"
            );
        }

        comisionDB.actualizarComisionEmpresa(comision);
    }

    private void validarEmpresa(ComisionEmpresa comision) {

        if (comision.getIdEmpresa() <= 0) {
            throw new IllegalArgumentException("ID de empresa inválido");
        }
    }

    private void validarPorcentaje(BigDecimal porcentaje) {

        if (porcentaje == null || porcentaje.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Porcentaje inválido");
        }

        if (porcentaje.compareTo(MAX_PORCENTAJE) > 0) {
            throw new IllegalArgumentException(
                    "El porcentaje no puede exceder el 25%"
            );
        }
    }
}
