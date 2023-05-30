package br.luahr.topicos1.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.luahr.topicos1.application.Result;
import br.luahr.topicos1.dto.FlorDTO;
import br.luahr.topicos1.dto.FlorResponseDTO;
import br.luahr.topicos1.service.FlorService;

@Path("/flores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlorResource {
    @Inject
    FlorService florService;

    @GET
    public List<FlorResponseDTO> getAll() {
         return florService.getAll();
    }

    @GET
    @Path("/{id}")
    public FlorResponseDTO findById(@PathParam("id") Long id) {
        return florService.findById(id);
    }

    @POST
    @Transactional
    public Response insert(FlorDTO florDTO) {
        try {
            FlorResponseDTO flor = florService.create(florDTO);
            return Response.status(Status.CREATED).entity(flor).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Long id, FlorDTO florDTO) {
        try {
            FlorResponseDTO flor = florService.update(id, florDTO);
            return Response.ok(flor).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        florService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return florService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<FlorResponseDTO> search(@PathParam("nome") String nome){
        return florService.findByNome(nome);
    }
}
