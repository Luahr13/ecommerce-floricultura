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
import br.luahr.topicos1.dto.TelefoneDTO;
import br.luahr.topicos1.dto.TelefoneResponseDTO;
import br.luahr.topicos1.service.TelefoneService;

@Path("/telefones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TelefoneResource {
    @Inject
    TelefoneService telefoneService;

    @GET
    public List<TelefoneResponseDTO> getAll() {
         return telefoneService.getAll();
    }

    @GET
    @Path("/{id}")
    public TelefoneResponseDTO findById(@PathParam("id") Long id) {
        return telefoneService.findById(id);
    }

    @POST
    @Transactional
    public Response insert(TelefoneDTO telefoneDTO) {
        try {
            TelefoneResponseDTO telefone = telefoneService.create(telefoneDTO);
            return Response.status(Status.CREATED).entity(telefone).build();
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
    public Response update(@PathParam("id") Long id, TelefoneDTO telefoneDTO) {
        try {
            TelefoneResponseDTO telefone = telefoneService.update(id, telefoneDTO);
            return Response.status(Status.NO_CONTENT).entity(telefone).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        telefoneService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return telefoneService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<TelefoneResponseDTO> search(@PathParam("nome") String nome){
        return telefoneService.findByNome(nome);
    } 
}
