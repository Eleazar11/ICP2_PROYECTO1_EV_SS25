/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.models;

import java.util.Date;

/**
 *
 * @author eleaz
 */
public class Empresa {
    private String nombre;
    private String descripcion;
    private Date fechaCreacionEmpresa;
    private boolean empresaActiva;

    public Empresa() {
    }

    public Empresa(String nombreEmpresa, String descripcion, Date fechaCreacionEmpresa, boolean empresaActiva) {
        this.nombre = nombreEmpresa;
        this.descripcion = descripcion;
        this.fechaCreacionEmpresa = fechaCreacionEmpresa;
        this.empresaActiva = empresaActiva;
    }

    
    
    public String getNombreEmpresa() {
        return nombre;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombre = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacionEmpresa() {
        return fechaCreacionEmpresa;
    }

    public void setFechaCreacionEmpresa(Date fechaCreacionEmpresa) {
        this.fechaCreacionEmpresa = fechaCreacionEmpresa;
    }

    public boolean isEmpresaActiva() {
        return empresaActiva;
    }

    public void setEmpresaActiva(boolean empresaActiva) {
        this.empresaActiva = empresaActiva;
    }
    
    
}
