package br.com.fiap.localhealth.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.localhealth.models.Diagnostico;


public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

}