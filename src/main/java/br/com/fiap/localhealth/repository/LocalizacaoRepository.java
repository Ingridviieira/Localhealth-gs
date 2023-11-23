package br.com.fiap.localhealth.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.localhealth.models.Localizacao;


public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    Page<Localizacao> findByNmCidadeContaining(String busca, Pageable pageable);
}


