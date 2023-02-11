package br.com.lucaslememoura.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.lucaslememoura.model.dto.LivroDTO;
import br.com.lucaslememoura.model.entity.Livro;

@Mapper(componentModel = "spring")
public interface LivroMapper {
    List<LivroDTO> parseListDTO(List<Livro> livros);

    @Mapping(source = "categoriaLivro", target = "categoriaLivro")
    @Mapping(source = "editoraLivro", target = "editoraLivro")
    LivroDTO parseDTO(Livro livro);

    @Mapping(source = "categoriaLivro", target = "categoriaLivro")
    @Mapping(source = "editoraLivro", target = "editoraLivro")
    Livro parseEntity(LivroDTO livro);
}
