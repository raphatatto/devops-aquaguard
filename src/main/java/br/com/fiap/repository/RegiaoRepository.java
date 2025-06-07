package br.com.fiap.repository;

import br.com.fiap.model.Regiao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
    Page<Regiao> findByCidadeContainingIgnoreCase(String cidade, Pageable pageable);
}