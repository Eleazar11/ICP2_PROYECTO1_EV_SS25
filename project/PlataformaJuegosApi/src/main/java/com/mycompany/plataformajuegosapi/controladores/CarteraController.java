/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.controladores;

import com.mycompany.plataformajuegosapi.servicios.CarteraService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author eleaz
 */
@Path("/cartera")
public class CarteraController {

    private final CarteraService carteraService = new CarteraService();

    @POST
    @Path("/recarga")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response recargar(Map<String, Object> body) {

        try {
            int idUsuario = (int) body.get("idUsuario");
            BigDecimal monto = new BigDecimal(body.get("monto").toString());

            carteraService.recargar(idUsuario, monto);

            return Response.ok(
                    "{\"mensaje\":\"Recarga realizada correctamente\"}"
            ).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al recargar cartera\"}")
                    .build();
        }
    }
}
