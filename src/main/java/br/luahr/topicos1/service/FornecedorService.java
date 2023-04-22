package br.luahr.topicos1.service;

import java.util.List;

import br.luahr.topicos1.dto.FornecedorDTO;
import br.luahr.topicos1.dto.FornecedorResponseDTO;

public interface FornecedorService {
    // recursos basicos
    List<FornecedorResponseDTO> getAll();

    FornecedorResponseDTO findById(Long id);

    FornecedorResponseDTO create(FornecedorDTO productDTO);

    FornecedorResponseDTO update(Long id, FornecedorDTO productDTO);

    void delete(Long id);

    // recursos extras

    List<FornecedorResponseDTO> findByNome(String nome);

    long count();
}
