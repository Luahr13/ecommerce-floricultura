package br.luahr.topicos1.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record ClienteDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String nome,

    @NotBlank(message = "O campo precisa ser preenchido.")
    @Pattern(regexp = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message = "O CPF deve estar no formato 999.999.999-99")
    String cpf,

    @NotNull(message = "O campo precisa ser preenchido.")
    Integer idSexo,

    @Valid
    @NotNull(message = "O telefone precisa ser informado.")
    Long telefone,

    @Valid
    @NotNull(message = "O endereço precisa ser informado.")
    Long endereco
) {
    
}