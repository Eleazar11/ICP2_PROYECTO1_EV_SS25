/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.servicios;

import com.mycompany.plataformajuegosapi.database.JuegosDB;
import com.mycompany.plataformajuegosapi.models.Juego;
import com.mycompany.plataformajuegosapi.models.JuegoMultimedia;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author eleaz
 */
public class JuegosService {

    private final JuegosDB juegosDB = new JuegosDB();

    public void registrarJuego(Juego juego) throws SQLException {

        if (juego.getTitulo() == null || juego.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título obligatorio");
        }

        if (juego.getPrecio() == null) {
            throw new IllegalArgumentException("Precio obligatorio");
        }

        if (juego.getIdEmpresa() <= 0) {
            throw new IllegalArgumentException("Empresa inválida");
        }

        try {
            juegosDB.registrarJuego(juego);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException("Ya existe un juego con ese título");
        }
    }

    public void actualizarJuego(Juego juego) throws SQLException {

        if (juego.getIdJuego() <= 0) {
            throw new IllegalArgumentException("ID de juego inválido");
        }

        if (juego.getTitulo() == null || juego.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }

        try {
            juegosDB.actualizarJuego(juego);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException("Ya existe un juego con ese título");
        }
    }

    public void agregarMultimedia(JuegoMultimedia multimedia) throws SQLException {

        if (multimedia.getIdJuego() <= 0) {
            throw new IllegalArgumentException("ID de juego inválido");
        }

        if (multimedia.getUrl() == null || multimedia.getUrl().isBlank()) {
            throw new IllegalArgumentException("URL obligatoria");
        }

        juegosDB.agregarMultimedia(multimedia);
    }

}
