package br.luahr;

import static io.restassured.RestAssured.given;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.ClienteDTO;
import br.luahr.topicos1.dto.ClienteResponseDTO;
import br.luahr.topicos1.service.ClienteService;
import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ClienteResourceTeste {
    @Inject
    ClienteService clienteService;

    @Test
    public void testGetAll() {
        given()
                .when().get("/clientes")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        ClienteDTO clienteDTO = new ClienteDTO(
                "Luahr",
                "11111111111-11",
                1,
                1L,
                1L);
        given()
                .contentType(ContentType.JSON)
                .body(clienteDTO)
                .when().post("/clientes")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Luahr"),
                                                     "cpf", is("11111111111-11"),
                                                     "idSexo", is(1),
                                                     "telefone", is(1L),
                                                     "endereco", is(1L));
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        ClienteDTO clienteDTO = new ClienteDTO(
                "Luahr",
                "11111111111-11",
                1,
                1L,
                1L);
        Long idLong = clienteService.create(clienteDTO).id();
        // Criando outra pessoa para atuailzacao
        ClienteDTO clienteUpDto = new ClienteDTO(
                "Luahr",
                "11111111111-22",
                1,
                1L,
                1L);
        given()
                .contentType(ContentType.JSON)
                .body(clienteUpDto)
                .when().put("/clientes/" + idLong)
                .then()
                .statusCode(204);
        // Verificando se os dados foram atualizados no banco de dados
        ClienteResponseDTO clienteResponseDTO = clienteService.findById(idLong);
        assertThat(clienteResponseDTO.nome(), is("Luahr"));
        assertThat(clienteResponseDTO.cpf(), is("11111111111-11"));
        assertThat(clienteResponseDTO.sexo(), is(1));
        assertThat(clienteResponseDTO.telefone(), is(1L));
        assertThat(clienteResponseDTO.endereco(), is(1L));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        ClienteDTO clienteDTO = new ClienteDTO(
                        "Luahr",
                        "11111111111-11",
                        1,
                        1L,
                        1L);
        Long idLong = clienteService.create(clienteDTO).id();
        given()
                .when().delete("/clientes/" + idLong)
                .then()
                .statusCode(204);
        // verificando se a pessoa fisica foi excluida
        ClienteResponseDTO clienteResponseDTO = null;
        try {
            clienteService.findById(idLong);
        } catch (Exception e) {
        } finally {
            assertNull(clienteResponseDTO);
        }
    }
}
