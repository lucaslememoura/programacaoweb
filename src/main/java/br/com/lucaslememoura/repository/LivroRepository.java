package br.com.lucaslememoura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucaslememoura.model.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
