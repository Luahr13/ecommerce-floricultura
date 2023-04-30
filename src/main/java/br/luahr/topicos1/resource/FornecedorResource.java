package br.luahr.topicos1.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import br.luahr.topicos1.dto.FornecedorDTO;
import br.luahr.topicos1.dto.FornecedorResponseDTO;
import br.luahr.topicos1.service.FornecedorService;

@Path("/fornecedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FornecedorResource {
    @Inject
    FornecedorService fornecedorService;

    @GET
    public List<FornecedorResponseDTO> getAll() {
         return fornecedorService.getAll();
    }

    @GET
    @Path("/{id}")
    public FornecedorResponseDTO findById(@PathParam("id") Long id) {
        return fornecedorService.findById(id);
    }

    @POST
    @Transactional
    public Response insert(FornecedorDTO fornecedorDTO) {
        try {
            FornecedorResponseDTO fornecedor = fornecedorService.create(fornecedorDTO);
            return Response.status(Status.CREATED).entity(fornecedor).build();
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
    public Response update(@PathParam("id") Long id, FornecedorDTO fornecedorDTO) {
        try {
            FornecedorResponseDTO fornecedor = fornecedorService.update(id, fornecedorDTO);
            return Response.status(Status.NO_CONTENT).entity(fornecedor).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return fornecedorService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<FornecedorResponseDTO> search(@PathParam("nome") String nome){
        return fornecedorService.findByNome(nome);
    }
}
