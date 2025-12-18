/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author eleaz
 */
@Path("/usuarios")
public class UsuarioResource {

    private static final String AVATAR_DIR =
            "C:/plataforma_videojuegos/uploads/avatars/";

    @POST
    @Path("/{id}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subirAvatar(
            @PathParam("id") int idUsuario,
            @FormDataParam("file") InputStream archivo,
            @FormDataParam("file") FormDataContentDisposition info
    ) {

        System.out.println("📥 Subida de avatar usuario ID: " + idUsuario);

        try {
            if (archivo == null || info == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Archivo requerido\"}")
                        .build();
            }

            String nombreOriginal = info.getFileName().toLowerCase();

            if (!nombreOriginal.matches(".*\\.(png|jpg|jpeg)$")) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Formato no permitido\"}")
                        .build();
            }

            Files.createDirectories(java.nio.file.Path.of(AVATAR_DIR));

            String extension =
                    nombreOriginal.substring(nombreOriginal.lastIndexOf('.'));

            String nombreFinal = idUsuario + extension;

            java.nio.file.Path destino =
                    java.nio.file.Path.of(AVATAR_DIR, nombreFinal);

            Files.copy(archivo, destino, StandardCopyOption.REPLACE_EXISTING);

            String rutaRelativa = "/uploads/avatars/" + nombreFinal;

            return Response.ok()
                    .entity("{\"avatar\":\"" + rutaRelativa + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"Error al subir avatar\"}")
                    .build();
        }
    }
}
