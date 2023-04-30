package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record FornecedorDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String nome,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String país,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String safra,
    @NotNull(message = "O campo precisa ser preenchido.")
    Float volume
) {

}
