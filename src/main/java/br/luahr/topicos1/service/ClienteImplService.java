package br.luahr.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.luahr.topicos1.dto.ClienteDTO;
import br.luahr.topicos1.dto.ClienteResponseDTO;
import br.luahr.topicos1.model.Cliente;
import br.luahr.topicos1.model.Sexo;
import br.luahr.topicos1.repository.ClienteRepository;

@ApplicationScoped
public class ClienteImplService implements ClienteService{

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    Validator validator;

    @Override
    public List<ClienteResponseDTO> getAll() {
        return clienteRepository.findAll()
                                        .stream()
                                        .map(ClienteResponseDTO::new)
                                        .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null){
            throw new NotFoundException("Não encontrado");
        }
        return new ClienteResponseDTO(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO create(ClienteDTO clienteDTO) throws ConstraintViolationException {
        validar(clienteDTO);

        Cliente entity = new Cliente();
        entity.setNome(clienteDTO.nome());
        entity.setCpf(clienteDTO.cpf());
        entity.setSenha(clienteDTO.senha());
        entity.setEmail(clienteDTO.email());
        entity.setSexo(Sexo.valueOf(clienteDTO.sexo()));
        entity.setTelefones(clienteDTO.telefone());
        entity.setEnderecos(clienteDTO.endereco());

        clienteRepository.persist(entity);

        return new ClienteResponseDTO(entity);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(Long id, ClienteDTO clienteDTO) throws ConstraintViolationException{
        validar(clienteDTO);
   
        Cliente entity = new Cliente();
        entity.setNome(clienteDTO.nome());
        entity.setCpf(clienteDTO.cpf());
        entity.setSenha(clienteDTO.senha());
        entity.setEmail(clienteDTO.email());
        entity.setSexo(Sexo.valueOf(clienteDTO.sexo()));
        entity.setTelefones(clienteDTO.telefone());
        entity.setEnderecos(clienteDTO.endereco());

        return new ClienteResponseDTO(entity);
    }


    private void validar(ClienteDTO clienteDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) throws IllegalArgumentException, NotFoundException{
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Cliente cliente = clienteRepository.findById(id);

        if (clienteRepository.isPersistent(cliente)){
            clienteRepository.delete(cliente);
        }else
            throw new NotFoundException("Nenhum usuario encontrado");
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) throws NullPointerException{
        List<Cliente> list = clienteRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhum usuario encontrado");

        return list.stream()
                    .map(ClienteResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return clienteRepository.count();
    }
}
