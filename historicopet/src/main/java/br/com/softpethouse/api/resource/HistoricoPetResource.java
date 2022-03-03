package br.com.softpethouse.api.resource;

import br.com.softpethouse.api.dto.HistoricoPetDto;
import br.com.softpethouse.servico.HistoricoPetServico;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("historicopet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricoPetResource {

    @Inject
    HistoricoPetServico servico;

    @POST
    public Response novoPet(HistoricoPetDto dto) {
        try {
            return Response.created(
                            UriBuilder.fromResource(HistoricoPetResource.class)
                                    .path(servico.novoHistoricoPet(dto))
                                    .build())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE.getStatusCode(), e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizaPet(@PathParam("id") @Valid @NotNull int id, HistoricoPetDto dto) {
        try {
            return Response.ok(servico.atualizaHistoricoPet(id, dto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    public Response PetPorId(@PathParam("id") @Valid @NotNull int id) {
        try {
            return Response.ok(servico.historicoPet(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
    }

    @GET
    public Response Pets(@Valid @NotNull @BeanParam Paginacao paginacao) {
        return Response.ok(servico.historicosPet(paginacao)).build();
    }

    @DELETE
    @Path("{id}")
    public Response Pet(@PathParam("id") @Valid @NotNull int id) {
        try {
            return Response.ok(servico.deletaHistoricoPet(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
    }
}
