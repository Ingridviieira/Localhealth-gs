package br.com.fiap.localhealth.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.localhealth.models.Diagnostico;
import br.com.fiap.localhealth.models.Doenca;
import br.com.fiap.localhealth.models.Localizacao;
import br.com.fiap.localhealth.models.Medico;
import br.com.fiap.localhealth.repository.DiagnosticoRepository;
import br.com.fiap.localhealth.repository.DoencaRepository;
import br.com.fiap.localhealth.repository.LocalizacaoRepository;
import br.com.fiap.localhealth.repository.MedicoRepository;


@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    DiagnosticoRepository diagnosticoRepository;

    @Autowired
    DoencaRepository doencaRepository;

    @Autowired
    LocalizacaoRepository localizacaoRepository;

    @Autowired
    MedicoRepository medicoRepository;
    
    @Override
    public void run(String... args) throws Exception {

        // Criar Medicos
        Medico medico1 = new Medico(1L, "95396", "Dr. João", "Clinico geral");
        Medico medico2 = new Medico(2L, "12345", "Dra. Maria", "Cardiologista");
        medicoRepository.saveAll(List.of(medico1, medico2));

        // Crie Doencas
        Doenca doenca1 = new Doenca(1L,"95492", "Gripe","Febre, tosse, dor de cabeça");
        Doenca doenca2 = new Doenca(2L,"959372","Resfriado","Congestão nasal, espirros");
        doencaRepository.saveAll(List.of(doenca1, doenca2));

        // Criar Diagnosticos associados a Medicos e Doencas
        Diagnostico dg1 = new Diagnostico(1L,"05773-110", "23/10/23", medico1, doenca1);
        Diagnostico dg2 = new Diagnostico(2L,"12345-678", "24/10/23", medico2, doenca2);
        diagnosticoRepository.saveAll(List.of(dg1, dg2));

        // Crie Localizacoes associadas aos Diagnosticos
        Localizacao localizacao1 = new Localizacao(1L,"São Paulo", "SP","Rua Abc, 347", "Parque Regina", dg2);
        Localizacao localizacao2 = new Localizacao(2L,"Rio de Janeiro", "RJ","Avenida Xyz, 123", "Copacabana",dg1);
        
        localizacao1.setDiagnostico(dg1); // Associe a localizacao ao diagnostico
        localizacao2.setDiagnostico(dg2);
    }
}

