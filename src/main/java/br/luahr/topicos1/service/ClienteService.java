package br.luahr.topicos1.service;

import java.util.List;

import br.luahr.topicos1.dto.ClienteDTO;
import br.luahr.topicos1.dto.ClienteResponseDTO;

public interface ClienteService {
    // recursos basicos
    List<ClienteResponseDTO> getAll();

    ClienteResponseDTO findById(Long id);

    ClienteResponseDTO create(ClienteDTO productDTO);

    ClienteResponseDTO update(Long id, ClienteDTO productDTO);

    void delete(Long id);

    // recursos extras

    List<ClienteResponseDTO> findByNome(String nome);

    long count();
}
