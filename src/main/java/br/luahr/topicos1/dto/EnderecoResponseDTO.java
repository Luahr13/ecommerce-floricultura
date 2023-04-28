package br.luahr.topicos1.dto;

import br.luahr.topicos1.model.Endereco;
import br.luahr.topicos1.model.Municipio;

public record EnderecoResponseDTO(
    Long id,
    String bairro,
    String numero,
    String complemento,
    String cep,
    Municipio municipio
) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(
            endereco.getId(),
            endereco.getBairro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getCep(),
            endereco.getMunicipio()
        );
    }
}
