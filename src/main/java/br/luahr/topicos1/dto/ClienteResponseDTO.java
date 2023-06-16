package br.luahr.topicos1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.luahr.topicos1.model.Cliente;
import br.luahr.topicos1.model.Sexo;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String cpf,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Sexo sexo,
    TelefoneResponseDTO telefone,
    EnderecoResponseDTO endereco,

    String login,

    String nomeImagem
) {
    public ClienteResponseDTO (Cliente cliente){
            this(cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getSexo(),
                new TelefoneResponseDTO(cliente.getTelefone()),
                new EnderecoResponseDTO(cliente.getEndereco()),
                cliente.getLogin(),
                cliente.getNomeImagem()
                );
    }
}