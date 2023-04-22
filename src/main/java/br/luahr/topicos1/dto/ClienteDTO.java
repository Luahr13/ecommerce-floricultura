package br.luahr.topicos1.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.luahr.topicos1.model.Endereco;
import br.luahr.topicos1.model.Telefone;

public record ClienteDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String nome,

    @NotBlank(message = "O campo precisa ser preenchido.")
    @Pattern(regexp = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message = "O CPF deve estar no formato 999.999.999-99")
    String cpf,

    @NotBlank(message = "O campo precisa ser preenchido.")
    @Size(max = 6, message = "A senha deve possuir no máximo 6 caracteres.")
    String senha,

    @NotBlank(message = "O campo precisa ser preenchido.")
    String email,

    @NotNull(message = "O campo precisa ser preenchido.")
    Integer sexo,

    @Valid
    Telefone telefone,

    @Valid
    Endereco endereco
) {
    
}