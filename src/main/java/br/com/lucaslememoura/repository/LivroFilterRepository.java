package br.com.lucaslememoura.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import br.com.lucaslememoura.model.entity.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class LivroFilterRepository extends QuerydslRepositorySupport {
    @PersistenceContext
    private EntityManager em;

    public LivroFilterRepository() {
        super(Livro.class);
    }

    @Transactional
    public List<Livro> filter(Livro filter){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Livro> cq =  cb.createQuery(Livro.class);
        Root<Livro> root = cq.from(Livro.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getNome()!=null) {
            predicates.add((Predicate) cb.like(cb.upper(root.get("nome")), "%"+filter.getNome().toUpperCase()+"%"));
        }

        if(filter.getIsbn()!=null) {
            predicates.add((Predicate) cb.like(cb.upper(root.get("isbn")), "%"+filter.getIsbn().toUpperCase()+"%" ));
        }

        cq.where(cb.and((Predicate[]) predicates.toArray(new Predicate[predicates.size()])));

        List<Livro> livros = em.createQuery(cq).getResultList();
        return livros;
    }

}