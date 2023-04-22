package br.luahr.topicos1.dto;

import br.luahr.topicos1.model.Flor;
import br.luahr.topicos1.model.Fornecedor;
import br.luahr.topicos1.model.TipoFlor;

public record FlorResponseDTO(
    Long id,
    String nome,
    String descricao,
    Float valorUnidade,
    String corPetalas,
    Double alturaCaule,
    TipoFlor tipoFlor,
    Fornecedor fornecedor
) {
    public FlorResponseDTO(Flor flor) {
        this(
            flor.getId(),
            flor.getNome(),
            flor.getDescricao(),
            flor.getValorUnidade(),
            flor.getCorPetalas(),
            flor.getAlturaCaule(),
            flor.getTipoFlor(),
            flor.getFornecedor()
        );
    }
}
