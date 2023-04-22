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

import br.luahr.topicos1.dto.MunicipioDTO;
import br.luahr.topicos1.dto.MunicipioResponseDTO;
import br.luahr.topicos1.model.Municipio;
import br.luahr.topicos1.repository.MunicipioRepository;

@ApplicationScoped
public class MunicipioImplService implements MunicipioService{

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    Validator validator;
    
    @Override
    public List<MunicipioResponseDTO> getAll() {
        return municipioRepository.findAll()
                                        .stream()
                                        .map(MunicipioResponseDTO::new)
                                        .collect(Collectors.toList());
    }

    @Override
    public MunicipioResponseDTO findById(Long id) {
        Municipio municipio = municipioRepository.findById(id);
        if (municipio == null){
            throw new NotFoundException("Não encontrado");
        }
        return new MunicipioResponseDTO(municipio);
    }

    @Override
    @Transactional
    public MunicipioResponseDTO create(MunicipioDTO municipioDTO) throws ConstraintViolationException{
        validar(municipioDTO);

        Municipio entity = new Municipio();
        entity.setNome(municipioDTO.nome());
        entity.setEstado(municipioDTO.idEstado());

        municipioRepository.persist(entity);

        return new MunicipioResponseDTO(entity);

    }

    @Override
    @Transactional
    public MunicipioResponseDTO update(Long id, MunicipioDTO municipioDTO) throws ConstraintViolationException{
        validar(municipioDTO);

        Municipio entity = new Municipio();
        entity.setNome(municipioDTO.nome());
        entity.setEstado(municipioDTO.idEstado());

        return new MunicipioResponseDTO(entity);
    }

    private void validar(MunicipioDTO municipioDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<MunicipioDTO>> violations = validator.validate(municipioDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) throws IllegalArgumentException, NotFoundException{
        if (id == null){
            throw new IllegalArgumentException("Número inválido");
        }
        Municipio municipio = municipioRepository.findById(id);

        if (municipioRepository.isPersistent(municipio)){
            municipioRepository.delete(municipio);
        }else
            throw new NotFoundException("Nenhum usuario encontrado");
    }

    @Override
    public List<MunicipioResponseDTO> findByNome(String nome) throws NullPointerException{
        List<Municipio> list = municipioRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhum usuario encontrado");

        return list.stream()
                    .map(MunicipioResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return municipioRepository.count();
    }
    
}
