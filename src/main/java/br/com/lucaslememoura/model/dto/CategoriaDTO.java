package br.com.lucaslememoura.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
        Long id,
        @NotBlank(message = "O campo nome é obrigatório") String nome
) {
    public CategoriaDTO(String nome) {
        this(null, nome);
    }

}
