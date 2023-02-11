package br.com.lucaslememoura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucaslememoura.model.entity.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
