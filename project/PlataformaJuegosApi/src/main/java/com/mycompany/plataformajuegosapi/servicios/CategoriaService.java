/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.servicios;

import com.mycompany.plataformajuegosapi.database.CategoriaDB;
import com.mycompany.plataformajuegosapi.models.Categoria;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eleaz
 */
public class CategoriaService {

    private final CategoriaDB categoriaDB = new CategoriaDB();

    public void crearCategoria(Categoria categoria) throws SQLException {

        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        categoriaDB.crearCategoria(categoria);
    }

    public List<Categoria> obtenerCategorias() throws SQLException {
        return categoriaDB.obtenerTodas();
    }

    public void sugerirCategoria(Categoria categoria) throws SQLException {

        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        categoriaDB.sugerirCategoria(categoria);
    }

    public List<Categoria> obtenerCategoriasActivas() throws SQLException {
        return categoriaDB.obtenerActivas();
    }

    public List<Categoria> obtenerCategoriasNoActivas() throws SQLException {
        return categoriaDB.obtenerNoActivas();
    }

    public void cambiarEstadoCategoria(int idCategoria, boolean activa) throws SQLException {

        if (idCategoria <= 0) {
            throw new IllegalArgumentException("ID de categoría inválido");
        }

        categoriaDB.actualizarEstado(idCategoria, activa);
    }

}
