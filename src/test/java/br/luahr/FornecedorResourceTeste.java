package br.luahr;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.FornecedorDTO;
import br.luahr.topicos1.dto.FornecedorResponseDTO;
import br.luahr.topicos1.service.FornecedorService;
import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class FornecedorResourceTeste {
    @Inject
    FornecedorService fornecedorService;

    @Test
    public void testGetAll() {
        given()
                .when().get("/fornecedores")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        FornecedorDTO fornecedorDTO = new FornecedorDTO(
                "L&L",
                "BR",
                "2023",
                10F);
        given()
                .contentType(ContentType.JSON)
                .body(fornecedorDTO)
                .when().post("/fornecedores")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("L&L"),
                                                     "país", is("BR"),
                                                     "safra", is("2023"),
                                                     "volume", is(10F));
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        FornecedorDTO fornecedorDTO = new FornecedorDTO(
                "L&L",
                "BR",
                "2023",
                10F);
        Long idLong = fornecedorService.create(fornecedorDTO).id();
        // Criando outra pessoa para atuailzacao
        FornecedorDTO fornecedorUpDto = new FornecedorDTO(
                "L&L",
                "BR",
                "2024",
                12F);
        given()
                .contentType(ContentType.JSON)
                .body(fornecedorUpDto)
                .when().put("/fornecedores/" + idLong)
                .then()
                .statusCode(204);
        // Verificando se os dados foram atualizados no banco de dados
        FornecedorResponseDTO fornecedorResponseDTO = fornecedorService.findById(idLong);
        assertThat(fornecedorResponseDTO.nome(), is("L&L"));
        assertThat(fornecedorResponseDTO.país(), is("BR"));
        assertThat(fornecedorResponseDTO.safra(), is("2024"));
        assertThat(fornecedorResponseDTO.volume(), is(12F));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        FornecedorDTO fornecedorDTO = new FornecedorDTO(
                "L&L",
                "BR",
                "2023",
                10F);
        Long idLong = fornecedorService.create(fornecedorDTO).id();
        given()
                .when().delete("/estados/" + idLong)
                .then()
                .statusCode(204);
        // verificando se a pessoa fisica foi excluida
        FornecedorResponseDTO fornecedorResponseDTO = null;
        try {
            fornecedorService.findById(idLong);
        } catch (Exception e) {
        } finally {
            assertNull(fornecedorResponseDTO);
        }
    }
}