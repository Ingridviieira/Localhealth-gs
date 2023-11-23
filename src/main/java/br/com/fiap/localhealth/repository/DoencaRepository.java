package br.com.fiap.localhealth.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.localhealth.models.Doenca;

public interface DoencaRepository extends JpaRepository<Doenca, Long> {
    Page<Doenca> findByNmDoencaContaining(String busca, Pageable pageable);
}
