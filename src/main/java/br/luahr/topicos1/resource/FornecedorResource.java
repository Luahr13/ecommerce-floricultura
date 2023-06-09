package br.luahr.topicos1.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
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
    @RolesAllowed({"Admin", "User"})
    public List<FornecedorResponseDTO> getAll() {
         return fornecedorService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin", "User"})
    public FornecedorResponseDTO findById(@PathParam("id") Long id) {
        return fornecedorService.findById(id);
    }

    @POST
    @Transactional
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({"Admin", "User"})
    public long count(){
        return fornecedorService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({"Admin", "User"})
    public List<FornecedorResponseDTO> search(@PathParam("nome") String nome){
        return fornecedorService.findByNome(nome);
    }
}
