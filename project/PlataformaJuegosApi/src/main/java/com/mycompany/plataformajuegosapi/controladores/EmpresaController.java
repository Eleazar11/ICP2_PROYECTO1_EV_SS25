/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.controladores;

import com.mycompany.plataformajuegosapi.models.RegistroEmpresaDTO;
import com.mycompany.plataformajuegosapi.servicios.EmpresaService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author eleaz
 */
@Path("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService = new EmpresaService();

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarEmpresa(RegistroEmpresaDTO dto) {

        try {
            empresaService.registrarEmpresaConUsuario(
                    dto.getEmpresa(),
                    dto.getUsuario()
            );

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Empresa y usuario creados correctamente\"}")
                    .build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\":\"Error al registrar empresa\"}")
                    .build();
        }
    }
}
