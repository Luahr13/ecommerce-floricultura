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
import br.luahr.topicos1.dto.EnderecoDTO;
import br.luahr.topicos1.dto.EnderecoResponseDTO;
import br.luahr.topicos1.service.EnderecoService;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoResource {
    @Inject
    private EnderecoService enderecoService;

    @GET
    public List<EnderecoResponseDTO> getAll() {
         return enderecoService.getAll();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponseDTO findById(@PathParam("id") Long id) {
        return enderecoService.findById(id);
    }

    @POST
    @Transactional
    public Response insert(EnderecoDTO enderecoDTO) {
        try {
            EnderecoResponseDTO endereco = enderecoService.create(enderecoDTO);
            return Response.status(Status.CREATED).entity(endereco).build();
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
    public Response update(@PathParam("id") Long id, EnderecoDTO enderecoDTO) {
        try {
            EnderecoResponseDTO endereco = enderecoService.update(id, enderecoDTO);
            return Response.ok(endereco).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @Path("/count")
    public long count(){
        return enderecoService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<EnderecoResponseDTO> search(@PathParam("nome") String nome){
        return enderecoService.findByNome(nome);
    }
}
