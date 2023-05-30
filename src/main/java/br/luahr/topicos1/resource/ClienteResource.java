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
import br.luahr.topicos1.dto.ClienteDTO;
import br.luahr.topicos1.dto.ClienteResponseDTO;
import br.luahr.topicos1.service.ClienteService;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService clienteService;

    @GET
    public List<ClienteResponseDTO> getAll() {
         return clienteService.getAll();
    }

    @GET
    @Path("/{id}")
    public ClienteResponseDTO findById(@PathParam("id") Long id) {
        return clienteService.findById(id);
    }

    @POST
    @Transactional
    public Response insert(ClienteDTO clienteDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.create(clienteDTO);
            return Response.status(Status.CREATED).entity(cliente).build();
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
    public Response update(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.update(id, clienteDTO);
            return Response.ok(cliente).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return clienteService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<ClienteResponseDTO> search(@PathParam("nome") String nome){
        return clienteService.findByNome(nome);
    }
    
}
