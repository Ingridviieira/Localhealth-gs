package br.com.fiap.localhealth.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.localhealth.models.Medico;


public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByNmMedicoContaining(String busca, Pageable pageable);
}