package br.luahr.topicos1.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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