package br.com.lucaslememoura.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lucaslememoura.model.dto.EditoraDTO;
import br.com.lucaslememoura.model.entity.Editora;
import br.com.lucaslememoura.model.mapper.EditoraMapper;
import br.com.lucaslememoura.repository.EditoraRepository;
import br.com.lucaslememoura.service.EditoraService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EditoraServiceImpl implements EditoraService {
    private final EditoraRepository repository;
    private final EditoraMapper mapper;

    public EditoraServiceImpl(EditoraRepository repository, EditoraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<EditoraDTO> findAll() {
        return mapper.parseListDTO(repository.findAll());
    }

    @Override
    public EditoraDTO findById(Long id) {

        Optional<Editora> editoraOptional = repository.findById(id);
        if (editoraOptional.isPresent()) {
            Editora editora = editoraOptional.get();
            return mapper.parseDTO(editora);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public EditoraDTO create(EditoraDTO entity) {

        Editora editora = mapper.parseEntity(entity);
        editora.setId(null);
        repository.save(editora);
        return mapper.parseDTO(editora);
    }

    @Override
    public EditoraDTO edit(Long id, EditoraDTO entity) {

        if (repository.existsById(id)) {
            Editora editora = mapper.parseEntity(entity);
            editora.setId(id);
            editora = repository.save(editora);
            return mapper.parseDTO(editora);
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
