/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.plataformajuegosapi.controladores;

import com.mycompany.plataformajuegosapi.models.Rol;
import com.mycompany.plataformajuegosapi.models.Usuario;
import com.mycompany.plataformajuegosapi.servicios.UsuarioService;
import com.mycompany.plataformajuegosapi.util.GeneradorToken;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author eleaz
 */
@Path("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;
    private static final String SECRET_KEY = "clave_usr_bati_juegos";

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario usuario) {

        System.out.println("? /usuarios/registro llamado");

        try {
            usuarioService.registrarUsuario(usuario);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\":\"Usuario registrado correctamente\"}")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace(); // importante para ver error real
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error al registrar usuario\"}")
                    .build();
        }
    }

    /**
     * Método para autenticar a un usuario mediante sus credenciales.
     *
     * @param usuario El objeto Usuario con el nombre de usuario y la contraseña
     * a autenticar.
     * @return Response con el resultado de la operación.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario usuario) {

        try {
            Usuario usuarioAutenticado = usuarioService.autenticarUsuario(
                    usuario.getCorreo(),
                    usuario.getContrasena()
            );

            GeneradorToken generadorToken = new GeneradorToken();
            String token = generadorToken.crearTokenJWT(usuarioAutenticado);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("token", token);
            respuesta.put("idUsuario", usuarioAutenticado.getIdUsuario());
            respuesta.put("correo", usuarioAutenticado.getCorreo());
            respuesta.put("rol", usuarioAutenticado.getRol());

            return Response.ok(respuesta).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", e.getMessage()))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(Map.of("error", "Error en el login"))
                    .build();
        }
    }

}
