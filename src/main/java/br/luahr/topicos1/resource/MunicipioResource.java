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
import br.luahr.topicos1.dto.MunicipioDTO;
import br.luahr.topicos1.dto.MunicipioResponseDTO;
import br.luahr.topicos1.service.MunicipioService;

@Path("/municipios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MunicipioResource {
    @Inject
    MunicipioService municipioService;

    @GET
    @RolesAllowed({"Admin", "User"})
    public List<MunicipioResponseDTO> getAll() {
         return municipioService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin", "User"})
    public MunicipioResponseDTO findById(@PathParam("id") Long id) {
        return municipioService.findById(id);
    }

    @POST
    @Transactional
    @RolesAllowed({"Admin"})
    public Response insert(MunicipioDTO municipioDTO) {
        try {
            MunicipioResponseDTO municipio = municipioService.create(municipioDTO);
            return Response.status(Status.CREATED).entity(municipio).build();
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
    public Response update(@PathParam("id") Long id, MunicipioDTO municipioDTO) {
        try {
            MunicipioResponseDTO municipio = municipioService.update(id, municipioDTO);
            return Response.status(Status.NO_CONTENT).entity(municipio).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        municipioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({"Admin", "User"})
    public long count(){
        return municipioService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({"Admin", "User"})
    public List<MunicipioResponseDTO> search(@PathParam("nome") String nome){
        return municipioService.findByNome(nome);
    }
}
