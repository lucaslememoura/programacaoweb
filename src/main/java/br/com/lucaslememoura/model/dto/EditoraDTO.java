package br.com.lucaslememoura.model.dto;

import jakarta.validation.constraints.NotBlank;

public record EditoraDTO(
        Long id,
        @NotBlank(message = "O campo nome é obrigatório") String nome,
        String descricao
) {
    public EditoraDTO(String nome, String descricao) {
        this(null, nome, descricao);
    }

}

