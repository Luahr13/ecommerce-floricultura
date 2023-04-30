package br.luahr;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.TelefoneDTO;
import br.luahr.topicos1.dto.TelefoneResponseDTO;
import br.luahr.topicos1.service.TelefoneService;
import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TelefoneResourseTeste {
    @Inject
    TelefoneService telefoneService;

    @Test
    public void testGetAll() {
        given()
                .when().get("/telefones")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        TelefoneDTO telefoneDTO = new TelefoneDTO(
                "(63)",
                "(99) 99999-9999");
        given()
                .contentType(ContentType.JSON)
                .body(telefoneDTO)
                .when().post("/telefones")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                "codigoArea", is("(63)"),
                                             "numero", is("(99) 99999-9999"));
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        TelefoneDTO telefoneDTO = new TelefoneDTO(
                "(63)",
                "(63) 11111-1111");
        Long idTLong = telefoneService.create(telefoneDTO).id();
        // Criando outra pessoa para atuailzacao
        TelefoneDTO telefoneUpDto = new TelefoneDTO(
            "(62)",
            "(62) 11111-2222");
        given()
                .contentType(ContentType.JSON)
                .body(telefoneUpDto)
                .when().put("/telefones/" + idTLong)
                .then()
                .statusCode(204);
        // Verificando se os dados foram atualizados no banco de dados
        TelefoneResponseDTO estadoResponseDTO = telefoneService.findById(idTLong);
        assertThat(estadoResponseDTO.codigoArea(), is("(62)"));
        assertThat(estadoResponseDTO.numero(), is("(62) 11111-2222"));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        TelefoneDTO telefoneDTO = new TelefoneDTO(
                "(63)",
                "(63) 11111-1111");
        Long idLong = telefoneService.create(telefoneDTO).id();
        given()
                .when().delete("/telefones/" + idLong)
                .then()
                .statusCode(204);
        // verificando se a pessoa fisica foi excluida
        TelefoneResponseDTO telefoneResponseDTO = null;
        try {
            telefoneService.findById(idLong);
        } catch (Exception e) {
        } finally {
            assertNull(telefoneResponseDTO);
        }
    }
}
