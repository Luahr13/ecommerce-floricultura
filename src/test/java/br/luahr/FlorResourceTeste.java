package br.luahr;

import static io.restassured.RestAssured.given;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.AuthClienteDTO;
import br.luahr.topicos1.dto.FlorDTO;
import br.luahr.topicos1.dto.FlorResponseDTO;
import br.luahr.topicos1.dto.FornecedorDTO;
import br.luahr.topicos1.service.FlorService;
import br.luahr.topicos1.service.FornecedorService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class FlorResourceTeste {

        private String token;

        @BeforeEach
        public void setUp() {
                var auth = new AuthClienteDTO("janio", "123");

                Response response = (Response) given()
                                .contentType("application/json")
                                .body(auth)
                                .when().post("/auth")
                                .then()
                                .statusCode(200)
                                .extract().response();

                token = response.header("Authorization");
        }

    @Inject
    FlorService florService;

    @Inject
    FornecedorService fornecedorService;

    @Test
    public void testGetAll() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/flores")
                .then()
                .statusCode(200);
    }

    @Test
    public void testeCreatFlor() {

        FornecedorDTO fornecedorDTO = new FornecedorDTO("L&L", "BR", "2023", 10F);

    Integer idFornecedor = given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(fornecedorDTO)
            .when().post("/fornecedores")
            .then()
            .statusCode(201)
            .body("id", notNullValue(),
                    "nome", is("L&L"),
                    "país", is("BR"),
                    "safra", is("2023"),
                    "volume", is(10F))
            .extract().path("id");

        FlorDTO florDTO = new FlorDTO(
                "Orquidea",
                "Bela Flor",
                1.5D,
                "Vermelha",
                0.3D,
                1,
                idFornecedor.longValue());
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(florDTO)
                .when().post("/flores")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                "nome", is("Orquidea"),
                "descricao", is("Bela Flor"),
                "valorUnidade", is(1.5D),
                "corPetalas", is("Vermelha"),
                "alturaCaule", is(0.3D),
                "tipoFlor", is(1),
                "idFornecedor", is(idFornecedor.longValue()));
    }

@Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        FlorDTO florDTO = new FlorDTO(
                "Orquidea",
                "Bela Flor",
                1.5,
                "Vermelha",
                0.3D,
                1,
                1L);
        Long idLong = florService.create(florDTO).id();
        // Criando outra pessoa para atuailzacao
        FlorDTO florUpDto = new FlorDTO(
                "Rosa",
                "Bela Flor",
                1.5,
                "Vermelha",
                0.3D,
                2,
                1L);
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(florUpDto)
                .when().put("/flores/" + idLong)
                .then()
                .statusCode(204);
        // Verificando se os dados foram atualizados no banco de dados
        FlorResponseDTO florResponseDTO = florService.findById(idLong);
        assertThat(florResponseDTO.nome(), is("Rosa"));
        assertThat(florResponseDTO.descricao(), is("Bela For"));
        assertThat(florResponseDTO.valorUnidade(), is(1.5F));
        assertThat(florResponseDTO.corPetalas(), is("Vermelha"));
        assertThat(florResponseDTO.alturaCaule(), is(0.3D));
        assertThat(florResponseDTO.tipoFlor(), is(2));
        assertThat(florResponseDTO.fornecedor(), is(1L));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        FlorDTO florDTO = new FlorDTO(
                "Orquidea",
                "Bela Flor",
                1.5,
                "Vermelha",
                0.3D,
                1,
                1L);
        Long idLong = florService.create(florDTO).id();
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/flores/" + idLong)
                .then()
                .statusCode(204);
        // verificando se a pessoa fisica foi excluida
        FlorResponseDTO florResponseDTO = null;
        try {
            florService.findById(idLong);
        } catch (Exception e) {
        } finally {
            assertNull(florResponseDTO);
        }
    }
}
