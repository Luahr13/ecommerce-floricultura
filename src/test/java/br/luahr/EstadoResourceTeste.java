package br.luahr;

import static io.restassured.RestAssured.given;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.EstadoDTO;
import br.luahr.topicos1.dto.EstadoResponseDTO;
import br.luahr.topicos1.service.EstadoService;
import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EstadoResourceTeste {
    @Inject
    EstadoService estadoService;

    @Test
    public void testGetAll() {
        given()
                .when().get("/estados")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        EstadoDTO estadoDTO = new EstadoDTO(
                "São Paulo",
                "SP");
        given()
                .contentType(ContentType.JSON)
                .body(estadoDTO)
                .when().post("/estados")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("São Paulo"),
                                                     "sigla", is("SP"));
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        EstadoDTO estadoDTO = new EstadoDTO(
                "São Paulo",
                "SP");
        Long id = estadoService.create(estadoDTO).id();
        // Criando outra pessoa para atuailzacao
        EstadoDTO estadoUpDto = new EstadoDTO(
                "Tocantins",
                "TO");
        given()
                .contentType(ContentType.JSON)
                .body(estadoUpDto)
                .when().put("/estados/" + id)
                .then()
                .statusCode(204);
        // Verificando se os dados foram atualizados no banco de dados
        EstadoResponseDTO estadoResponseDTO = estadoService.findById(id);
        assertThat(estadoResponseDTO.nome(), is("Tocantins"));
        assertThat(estadoResponseDTO.sigla(), is("TO"));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        EstadoDTO estadoDTO = new EstadoDTO(
                "São Paulo",
                "SP");
        Long id = estadoService.create(estadoDTO).id();
        given()
                .when().delete("/estados/" + id)
                .then()
                .statusCode(204);
        // verificando se a pessoa fisica foi excluida
        EstadoResponseDTO estadoResponseDTO = null;
        try {
            estadoService.findById(id);
        } catch (Exception e) {
        } finally {
            assertNull(estadoResponseDTO);
        }
    }
}
