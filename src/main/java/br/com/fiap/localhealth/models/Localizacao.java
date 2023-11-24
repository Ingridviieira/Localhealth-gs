package br.com.fiap.localhealth.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.localhealth.controllers.LocalizacaoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.Builder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nmCidade;

    private String nmEstado;

    private String nmRua;

    private String nmBairro;

    @ManyToOne
    @JoinColumn(name = "diagnostico_id")
    private Diagnostico diagnostico;



}
    

