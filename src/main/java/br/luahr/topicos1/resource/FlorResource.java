package br.luahr.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;

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
import br.luahr.topicos1.dto.FlorDTO;
import br.luahr.topicos1.dto.FlorResponseDTO;
import br.luahr.topicos1.service.FlorService;

@Path("/flores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlorResource {
    @Inject
    FlorService florService;

    private static final Logger LOG = Logger.getLogger(FlorResource.class);

    @GET
    @RolesAllowed({ "Admin", "User" })
    public List<FlorResponseDTO> getAll() {
        LOG.info("Buscando Flores.");
        LOG.debug("Debug de busca de lista de flores.");
        return florService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin", "User" })
    public FlorResponseDTO findById(@PathParam("id") Long id) {
        LOG.info("Buscando ID de flores.");
        LOG.debug("Debug de busca de ID de flores.");
        return florService.findById(id);
    }

    @POST
    @Transactional
    @RolesAllowed({ "Admin" })
    public Response insert(FlorDTO florDTO) {
        LOG.info("Inserindo uma flor.");
        try {
            FlorResponseDTO flor = florService.create(florDTO);
            return Response.status(Status.CREATED).entity(flor).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de flores.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RolesAllowed({ "Admin" })
    public Response update(@PathParam("id") Long id, FlorDTO florDTO) {
        LOG.info("Atualiza uma flor.");
        try {
            FlorResponseDTO flor = florService.update(id, florDTO);
            return Response.ok(flor).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de updat de flores.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        LOG.info("deleta uma flor.");
        florService.delete(id);
        LOG.debug("Debug de deletar flores.");
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed({ "Admin", "User" })
    public long count() {
        LOG.info("Conta flores.");
        return florService.count();
    }

    @GET
    @Path("/search/{nome}")
    @RolesAllowed({ "Admin", "User" })
    public List<FlorResponseDTO> search(@PathParam("nome") String nome) {
        LOG.info("Busca nome de flores.");
        return florService.findByNome(nome);
    }
}
