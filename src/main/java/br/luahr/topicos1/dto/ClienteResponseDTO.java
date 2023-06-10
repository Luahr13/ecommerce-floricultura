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
    Endereco endereco,

    String login,

    String nomeImagem
) {
    public static ClienteResponseDTO valueOf(Cliente cliente){
        if(cliente.getPessoa() == null)
            return new ClienteResponseDTO(cliente.getId(),
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    cliente.getLogin(),
                                    null);
            return new ClienteResponseDTO(cliente.getId(),
                                        cliente.getPessoa().getNome(),
                                        cliente.getPessoa().getCpf(),
                                        cliente.getPessoa().getSexo(),
                                        cliente.getPessoa().getTelefone(),
                                        cliente.getPessoa().getEndereco(),
                                        cliente.getLogin(),
                                        cliente.getNomeImagem());
    }
}