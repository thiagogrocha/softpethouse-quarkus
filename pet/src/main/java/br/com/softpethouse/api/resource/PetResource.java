package br.com.softpethouse.api.resource;

import br.com.softpethouse.api.dto.PetDto;
import br.com.softpethouse.servico.PetServico;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("pet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @Inject
    PetServico servico;

    @POST
    public Response novoPet(PetDto dto) {
        try {
            return Response.created(
                            UriBuilder.fromResource(PetResource.class)
                                    .path(servico.novoPet(dto))
                                    .build())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE.getStatusCode(), e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizaPet(@PathParam("id") @Valid @NotNull int id, PetDto dto) {
        try {
            return Response.ok(servico.atualizaPet(id, dto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    public Response PetPorId(@PathParam("id") @Valid @NotNull int id) {
        try {
            return Response.ok(servico.pet(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    public Response Pets(@Valid @NotNull @BeanParam Paginacao paginacao) {
        return Response.ok(servico.pets(paginacao)).build();
    }

    @DELETE
    @Path("{id}")
    public Response Pet(@PathParam("id") @Valid @NotNull int id) {
        try {
            return Response.ok(servico.deletaPet(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
    }
}
