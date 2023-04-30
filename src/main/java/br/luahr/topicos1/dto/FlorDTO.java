package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record FlorDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String nome,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String descricao,
    @NotNull(message = "O campo precisa ser preenchido.")
    Float valorUnidade,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String corPetalas,
    @NotNull(message = "O campo precisa ser preenchido.")
    Double alturaCaule,
    @NotNull(message = "O campo precisa ser preenchido.")
    Integer  tipoFlor,
    @NotNull(message = "O campo precisa ser preenchido.")
    Long fornecedor
) {
    
}
