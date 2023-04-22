package br.luahr.topicos1.service;

import java.util.List;

import br.luahr.topicos1.dto.FlorDTO;
import br.luahr.topicos1.dto.FlorResponseDTO;

public interface FlorService {
    // recursos basicos
    List<FlorResponseDTO> getAll();

    FlorResponseDTO findById(Long id);

    FlorResponseDTO create(FlorDTO productDTO);

    FlorResponseDTO update(Long id, FlorDTO productDTO);

    void delete(Long id);

    // recursos extras

    List<FlorResponseDTO> findByNome(String nome);

    long count();
}
