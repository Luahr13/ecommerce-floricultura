package br.luahr.topicos1.dto;

import br.luahr.topicos1.model.Endereco;

public record EnderecoResponseDTO(
    String bairro,
    String numero,
    String complemento,
    String cep,
    MunicipioResponseDTO municipio
) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(
            endereco.getBairro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getCep(),
            new MunicipioResponseDTO(endereco.getMunicipio())
        );
    }
}

