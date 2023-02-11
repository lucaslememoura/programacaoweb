package br.com.lucaslememoura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lucaslememoura.model.dto.CategoriaDTO;
import br.com.lucaslememoura.model.entity.Categoria;
import br.com.lucaslememoura.model.mapper.CategoriaMapper;
import br.com.lucaslememoura.repository.CategoriaRepository;
import br.com.lucaslememoura.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;
    public CategoriaServiceImpl(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CategoriaDTO> findAll() {
        return mapper.parseListDTO(repository.findAll());
    }

    @Override
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoriaProdutoOp = repository.findById(id);
        if (categoriaProdutoOp.isPresent()) {
            Categoria categoria = categoriaProdutoOp.get();
            return mapper.parseDTO(categoria);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public CategoriaDTO create(CategoriaDTO entity) {
        Categoria categoria = mapper.parseEntity(entity);
        categoria.setId(null);
        repository.save(categoria);
        return mapper.parseDTO(categoria);
    }

    @Override
    public CategoriaDTO edit(Long id, CategoriaDTO entity) {
        if (repository.existsById(id)) {
            Categoria categoria = mapper.parseEntity(entity);
            categoria.setId(id);
            categoria = repository.save(categoria);
            return mapper.parseDTO(categoria);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }
}
