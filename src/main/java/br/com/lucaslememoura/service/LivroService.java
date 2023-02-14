package br.com.lucaslememoura.service;

import java.util.List;

import br.com.lucaslememoura.model.dto.LivroDTO;

public interface LivroService extends BaseService<LivroDTO> {
    List<LivroDTO> filtrar(LivroDTO livroDTO);
}