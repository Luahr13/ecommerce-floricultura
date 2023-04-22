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
import br.luahr.topicos1.dto.EstadoDTO;
import br.luahr.topicos1.dto.EstadoResponseDTO;
import br.luahr.topicos1.service.EstadoService;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {
    @Inject
    private EstadoService estadoService;

    @GET
    public List<EstadoResponseDTO> getAll() {
         return estadoService.getAll();
    }

    @GET
    @Path("/{id}")
    public EstadoResponseDTO findById(@PathParam("id") Long id) {
        return estadoService.findById(id);
    }

    @POST
    @Transactional
    public Response insert(EstadoDTO estadoDTO) {
        try {
            EstadoResponseDTO estado = estadoService.create(estadoDTO);
            return Response.status(Status.CREATED).entity(estado).build();
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
    public Response update(@PathParam("id") Long id, EstadoDTO estadoDTO) {
        try {
            EstadoResponseDTO estado = estadoService.update(id, estadoDTO);
            return Response.ok(estado).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @Path("/count")
    public long count(){
        return estadoService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<EstadoResponseDTO> search(@PathParam("nome") String nome){
        return estadoService.findByNome(nome);
    }
}
