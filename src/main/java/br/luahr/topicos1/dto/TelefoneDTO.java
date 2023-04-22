package br.luahr.topicos1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record TelefoneDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    @Size(max = 2, message = "O codigo área precisa ter 2 números.")
    String codigoArea,
    @NotBlank(message = "O campo precisa ser preenchido.")
    @Pattern(regexp = "^\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}$", message = "O número de telefone deve estar no formato (99) 99999-9999")
    String numero
) {

}