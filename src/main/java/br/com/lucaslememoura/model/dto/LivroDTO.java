package br.com.lucaslememoura.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDTO(
        Long id,
        @NotNull(message = "O campo Categoria é obrigatório!") CategoriaDTO categoriaLivro,
        @NotNull(message = "O campo Editora é obrigatório!") EditoraDTO editoraLivro,
        @NotBlank(message = "O campo nome  é obrigatório!") String nome,
        @NotNull(message = "O campo isbn  é obrigatório!") String isbn
       ) {

    public LivroDTO(CategoriaDTO categoriaLivro, EditoraDTO editoraLivro, String nome, String isbn) {
        this(null,categoriaLivro, editoraLivro, nome,isbn);
    }

}