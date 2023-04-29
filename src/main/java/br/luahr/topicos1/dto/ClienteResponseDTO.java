package br.luahr.topicos1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.luahr.topicos1.model.Cliente;
import br.luahr.topicos1.model.Endereco;
import br.luahr.topicos1.model.Sexo;
import br.luahr.topicos1.model.Telefone;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String cpf,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Sexo sexo,
    Telefone telefone,
    Endereco endereco
) {
    public ClienteResponseDTO(Cliente cliente){
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getSexo(),
            cliente.getTelefone(),
            cliente.getEndereco()
        );
    }
}