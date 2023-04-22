package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.luahr.topicos1.model.Estado;

public record MunicipioDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,
    @NotNull(message = "O campo idEstado deve ser informado.")
    Estado idEstado)
{
    
}