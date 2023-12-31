package br.com.fiap.localhealth.models;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.localhealth.controllers.DoencaController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_doenca")
public class Doenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidDoenca;

    private String nmDoenca;

    private String dsSintomas;


    public EntityModel<Doenca> toEntityModel() {
        return EntityModel.of(
            this, 
            linkTo(methodOn(DoencaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(DoencaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(DoencaController.class).index(null, Pageable.unpaged())).withRel("all")
        );
    }
    
}