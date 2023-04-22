package br.luahr.topicos1.dto;

import java.util.HashMap;
import java.util.Map;

import br.luahr.topicos1.model.Municipio;

public record MunicipioResponseDTO(
    Long id,
    String nome,
    Map<String, Object> estado
) {

    public MunicipioResponseDTO(Municipio municipio) {
        
        this(municipio.getId(),
            municipio.getNome(),
            viewEstado(municipio.getEstado().getNome(), municipio.getEstado().getSigla()));
    }

    public static Map<String, Object> viewEstado(String nome, String sigla) {

        Map<String, Object> estado = new HashMap<>();

        estado.put("nome:", nome);
        estado.put("sigla:", sigla);

        return estado;
    }
}
