package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;

public record FornecedorDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String nome,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String país,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String safra,
    @NotBlank(message = "O campo precisa ser preenchido.")
    Float volume
) {

}
