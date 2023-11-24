package br.com.fiap.localhealth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_diagnostico")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nrCep;

    private String dtDiagnostico;

     // Relacionamento com a tabela Medico
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

     // Relacionamento com a tabela Doenca
    @ManyToOne
    @JoinColumn(name = "doenca_id")
    private Doenca doenca;


}
