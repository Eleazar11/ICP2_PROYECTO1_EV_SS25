/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.controladores;

import com.mycompany.plataformajuegosapi.models.ComisionEmpresa;
import com.mycompany.plataformajuegosapi.models.ComisionGlobal;
import com.mycompany.plataformajuegosapi.servicios.ComisionService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author eleaz
 */
@Path("/comisiones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComisionController {

    private final ComisionService comisionService = new ComisionService();

    @POST
    @Path("/global")
    public Response crearComisionGlobal(ComisionGlobal comision) {

        try {
            comisionService.crearComisionGlobal(comision);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Comisión global registrada correctamente\"}")
                    .build();

        } catch (IllegalArgumentException | IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al crear la comisión global\"}")
                    .build();
        }
    }

    @POST
    @Path("/empresa")
    public Response crearComisionEmpresa(ComisionEmpresa comision) {

        try {
            comisionService.crearComisionEmpresa(comision);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Comisión por empresa registrada correctamente\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al crear comisión por empresa\"}")
                    .build();
        }
    }

    @PUT
    @Path("/empresa")
    public Response actualizarComisionEmpresa(ComisionEmpresa comision) {

        try {
            comisionService.actualizarComisionEmpresa(comision);

            return Response.ok(
                    "{\"mensaje\":\"Comisión de empresa actualizada correctamente\"}"
            ).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al actualizar comisión por empresa\"}")
                    .build();
        }
    }
}
