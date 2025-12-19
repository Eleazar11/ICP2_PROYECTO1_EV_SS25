/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.controladores;

import com.mycompany.plataformajuegosapi.models.Categoria;
import com.mycompany.plataformajuegosapi.servicios.CategoriaService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author eleaz
 */
@Path("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService = new CategoriaService();

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCategoria(Categoria categoria) {

        try {
            categoriaService.crearCategoria(categoria);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Categoría creada correctamente\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al crear la categoría\"}")
                    .build();
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCategorias() {

        try {
            return Response.ok(categoriaService.obtenerCategorias()).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al obtener categorías\"}")
                    .build();
        }
    }

    @POST
    @Path("/sugerir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sugerirCategoria(Categoria categoria) {

        try {
            categoriaService.sugerirCategoria(categoria);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Categoría sugerida correctamente, pendiente de aprobación\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al sugerir la categoría\"}")
                    .build();
        }
    }

    @GET
    @Path("/activas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCategoriasActivas() {

        try {
            return Response.ok(categoriaService.obtenerCategoriasActivas()).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al obtener categorías activas\"}")
                    .build();
        }
    }

    @GET
    @Path("/pendientes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCategoriasNoActivas() {

        try {
            return Response.ok(categoriaService.obtenerCategoriasNoActivas()).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al obtener categorías pendientes\"}")
                    .build();
        }
    }

    @PUT
    @Path("/estado/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarEstadoCategoria(
            @PathParam("id") int idCategoria,
            Categoria categoria) {

        try {
            categoriaService.cambiarEstadoCategoria(idCategoria, categoria.isActiva());

            return Response.ok("{\"mensaje\":\"Estado de la categoría actualizado\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al actualizar el estado de la categoría\"}")
                    .build();
        }
    }

}
