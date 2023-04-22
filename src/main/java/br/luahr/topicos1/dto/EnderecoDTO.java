package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.luahr.topicos1.model.Municipio;

public record EnderecoDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String bairro,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String numero,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String complemento,
    @NotBlank(message = "O campo precisa ser preenchido.")
    @Pattern(regexp = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message = "O CEP deve estar no formato 99999-999") String cep,
    @NotBlank(message = "O campo precisa ser preenchido.")
    Municipio municipio
) {

}