package br.luahr;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.TelefoneResponseDTO;
import br.luahr.topicos1.service.TelefoneService;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class TelefoneResourceTeste {

    @Inject
    TelefoneService telefoneService;

    @Test
    public void testGetAllTelefones() {
        List<TelefoneResponseDTO> expectedTelefones = Arrays.asList(
                new TelefoneResponseDTO((long) 10, "63", "(63) 11111-1111"),
                new TelefoneResponseDTO((long)10, "11", "(11) 22222-2222")
        );

        List<TelefoneResponseDTO> actualTelefones = given()
                .when().get("/telefones")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList(".", TelefoneResponseDTO.class);

        assertEquals(expectedTelefones, actualTelefones);
    }

}
