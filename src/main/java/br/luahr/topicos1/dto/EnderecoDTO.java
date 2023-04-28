package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record EnderecoDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String bairro,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String numero,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String complemento,
    @NotBlank(message = "O campo precisa ser preenchido.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")String cep,
    @NotNull(message = "O campo precisa ser preenchido.")
    Long idMunicipio
) {

}