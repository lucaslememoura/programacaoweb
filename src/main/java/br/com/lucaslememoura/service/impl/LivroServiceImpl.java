package br.com.lucaslememoura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lucaslememoura.model.dto.LivroDTO;
import br.com.lucaslememoura.model.entity.Livro;
import br.com.lucaslememoura.model.mapper.LivroMapper;
import br.com.lucaslememoura.repository.LivroRepository;
import br.com.lucaslememoura.service.LivroService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository repository;
    private final LivroMapper mapper;

    public LivroServiceImpl(LivroRepository repository, LivroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LivroDTO> findAll() {
        return mapper.parseListDTO(repository.findAll());
    }

    @Override
    public LivroDTO findById(Long id) {
        Optional<Livro> livroOptional = repository.findById(id);
        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();
            return mapper.parseDTO(livro);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public LivroDTO create(LivroDTO entity) {

        Livro livro = mapper.parseEntity(entity);
        livro.setId(null);
        repository.save(livro);
        return mapper.parseDTO(livro);
    }

    @Override
    public LivroDTO edit(Long id, LivroDTO entity) {
        if (repository.existsById(id)) {
            Livro livro = mapper.parseEntity(entity);
            livro.setId(id);
            livro = repository.save(livro);
            return mapper.parseDTO(livro);
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
