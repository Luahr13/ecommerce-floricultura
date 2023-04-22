package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;

public record FlorDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String nome,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String descricao,
    @NotBlank(message = "O campo precisa ser preenchido.")
    Float valorUnidade,
    @NotBlank(message = "O campo precisa ser preenchido.")
    String corPetalas,
    @NotBlank(message = "O campo precisa ser preenchido.")
    Double alturaCaule,
    @NotBlank(message = "O campo precisa ser preenchido.")
    Integer  tipoFlor,
    @NotBlank(message = "O campo precisa ser preenchido.")
    Long fornecedor
) {
    
}
