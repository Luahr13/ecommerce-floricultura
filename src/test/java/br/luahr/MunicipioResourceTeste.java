package br.luahr;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.MunicipioDTO;
import br.luahr.topicos1.dto.MunicipioResponseDTO;
import br.luahr.topicos1.service.MunicipioService;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class MunicipioResourceTeste {

    @Inject
    MunicipioService municipioService;

    @Test
    public void testGetAll() {
        given()
                .when().get("/municipios")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {

        MunicipioDTO municipioDTO = new MunicipioDTO(
                "Palmas",
                1L);
        given()
                .contentType(ContentType.JSON)
                .body(municipioDTO)
                .when().post("/municipios")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("Palmas"),
                                                    "id_estado", is(1L));
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        MunicipioDTO municipioDTO = new MunicipioDTO(
                "Colinas",
                1L);
        Long id = municipioService.create(municipioDTO).id();
        // Criando outra pessoa para atuailzacao
        MunicipioDTO municipioUpDTO = new MunicipioDTO(
                "Palmas",
                1L);
        given()
                .contentType(ContentType.JSON)
                .body(municipioUpDTO)
                .when().put("/estados/" + id)
                .then()
                .statusCode(204);
        // Verificando se os dados foram atualizados no banco de dados
        MunicipioResponseDTO municipioResponseDTO = municipioService.findById(id);
        assertThat(municipioResponseDTO.nome(), is("Tocantins"));
        assertThat(municipioResponseDTO.estado().getId(), is(1L));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        MunicipioDTO municipioDTO = new MunicipioDTO(
                "Palmas",
                1L);
        Long id = municipioService.create(municipioDTO).id();
        given()
                .when().delete("/estados/" + id)
                .then()
                .statusCode(204);
        // verificando se a pessoa fisica foi excluida
        MunicipioResponseDTO municipioResponseDTO = null;
        try {
            municipioService.findById(id);
        } catch (Exception e) {
        } finally {
            assertNull(municipioResponseDTO);
        }
    }
    
}
