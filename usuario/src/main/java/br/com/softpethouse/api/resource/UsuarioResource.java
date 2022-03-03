package br.com.softpethouse.api.resource;

import br.com.softpethouse.api.dto.UsuarioDto;
import br.com.softpethouse.servico.UsuarioServico;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioServico servico;

    @POST
    public Response novoUsuario(UsuarioDto dto) {
        try {
            return Response.created(
                            UriBuilder.fromResource(UsuarioResource.class)
                                    .path(servico.novoUsuario(dto))
                                    .build())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE.getStatusCode(), e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizaUsuario(@PathParam("id") @Valid @NotNull int id, UsuarioDto dto) {
        try {
            return Response.ok(servico.atualizaUsuario(id, dto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    public Response usuarioPorId(@PathParam("id") @Valid @NotNull int id) {
        try {
            return Response.ok(servico.usuario(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    public Response usuarios(@Valid @NotNull @BeanParam Paginacao paginacao) {
        return Response.ok(servico.usuarios(paginacao)).build();
    }

    @DELETE
    @Path("{id}")
    public Response usuario(@PathParam("id") @Valid @NotNull int id) {
        try {
            return Response.ok(servico.deletaUsuario(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
    }
}