/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.controladores;

import com.mycompany.plataformajuegosapi.models.Juego;
import com.mycompany.plataformajuegosapi.models.JuegoMultimedia;
import com.mycompany.plataformajuegosapi.servicios.JuegosService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author eleaz
 */
@Path("/juegos")
public class JuegosController {

    private final JuegosService juegosService = new JuegosService();

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarJuego(Juego juego) {

        try {
            juegosService.registrarJuego(juego);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Juego registrado correctamente\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al registrar el juego\"}")
                    .build();
        }
    }

    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarJuego(Juego juego) {

        try {
            juegosService.actualizarJuego(juego);

            return Response.ok(
                    "{\"mensaje\":\"Juego actualizado correctamente\"}"
            ).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al actualizar el juego\"}")
                    .build();
        }
    }

    @POST
    @Path("/multimedia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarMultimedia(JuegoMultimedia multimedia) {

        try {
            juegosService.agregarMultimedia(multimedia);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Multimedia agregada correctamente\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al agregar multimedia\"}")
                    .build();
        }
    }

}
