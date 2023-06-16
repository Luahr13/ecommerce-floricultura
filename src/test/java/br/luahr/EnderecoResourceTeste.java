package br.luahr;

import static io.restassured.RestAssured.given;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.luahr.topicos1.dto.AuthClienteDTO;
import br.luahr.topicos1.dto.EnderecoDTO;
import br.luahr.topicos1.dto.EnderecoResponseDTO;
import br.luahr.topicos1.dto.EstadoDTO;
import br.luahr.topicos1.dto.MunicipioDTO;
import br.luahr.topicos1.model.Municipio;
import br.luahr.topicos1.service.EnderecoService;
import br.luahr.topicos1.service.EstadoService;
import br.luahr.topicos1.service.MunicipioService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EnderecoResourceTeste {

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
        EnderecoService enderecoService;

        @Inject
        MunicipioService municipioService;

        @Inject
        EstadoService estadoService;

        @Test
        public void testGetAll() {
                given()
                                .header("Authorization", "Bearer " + token)
                                .when().get("/estados")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                Long idEstado = estadoService.create(new EstadoDTO("Tocantins", "TO")).id();
                Long idMunicipio = municipioService.create(new MunicipioDTO("Palmas", idEstado)).id();

                EnderecoDTO enderecoDTO = new EnderecoDTO(
                                "Norte",
                                "13",
                                "05",
                                "11111-111",
                                idMunicipio);
                given()
                                .header("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .body(enderecoDTO)
                                .when().post("/enderecos")
                                .then()
                                .statusCode(201)
                                .body("id", notNullValue(),
                                                "bairro", is("Norte"),
                                                "numero", is("13"),
                                                "complemento", is("05"),
                                                "cep", is("11111-111"),
                                                "municipio", notNullValue(Municipio.class));
        }

        @Test
        public void testUpdate() {
                // Adicionando uma pessoa no banco de dados
                EnderecoDTO enderecoDTO = new EnderecoDTO(
                                "Norte",
                                "13",
                                "05",
                                "11111-111",
                                1L);
                Long idEdLong = enderecoService.create(enderecoDTO).id();
                // Criando outra pessoa para atuailzacao
                EnderecoDTO enderecoUpDto = new EnderecoDTO(
                                "Centro",
                                "13",
                                "05",
                                "11111-112",
                                1L);
                given()
                                .contentType(ContentType.JSON)
                                .body(enderecoUpDto)
                                .when().put("/enderecos/" + idEdLong)
                                .then()
                                .statusCode(204);
                // Verificando se os dados foram atualizados no banco de dados
                EnderecoResponseDTO estadoResponseDTO = enderecoService.findById(idEdLong);
                assertThat(estadoResponseDTO.bairro(), is("Centro"));
                assertThat(estadoResponseDTO.numero(), is("13"));
                assertThat(estadoResponseDTO.complemento(), is("05"));
                assertThat(estadoResponseDTO.cep(), is("11111-111"));
                assertThat(estadoResponseDTO.id(), is(1L));

        }

        @Test
        public void testDelete() {
                // Adicionando uma pessoa no banco de dados
                EnderecoDTO enderecoDTO = new EnderecoDTO(
                                "Norte",
                                "13",
                                "05",
                                "11111-111",
                                1L);
                Long idELong = enderecoService.create(enderecoDTO).id();
                given()
                                .when().delete("/estados/" + idELong)
                                .then()
                                .statusCode(204);
                // verificando se a pessoa fisica foi excluida
                EnderecoResponseDTO enderecoResponseDTO = null;
                try {
                        enderecoService.findById(idELong);
                } catch (Exception e) {
                } finally {
                        assertNull(enderecoResponseDTO);
                }
        }
}
