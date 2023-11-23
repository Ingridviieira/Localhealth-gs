package br.com.fiap.localhealth.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.localhealth.controllers.MedicoController;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_medico")
public class Medico  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String crm;
    private String nmMedico;
    private String especialidade;
  

    @ManyToOne
    private Diagnostico diagnostico;

        public EntityModel<Medico> toEntityModel() {
        return EntityModel.of(
            this, 
            linkTo(methodOn(MedicoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(MedicoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(MedicoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(MedicoController.class).show(this.getDiagnostico().getId())).withRel("diagnostico")
        );
    }

}