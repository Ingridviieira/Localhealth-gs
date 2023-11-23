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

        // Criar  Diagnostico
        Diagnostico dg1 = new Diagnostico(1L,"05773-110", (LocalDate.now()));
        diagnosticoRepository.saveAll(List.of(dg1));
        
        Medico medico1 = new Medico(1L, "95396", "Dr. João", "Clinico geral", dg1);
        medicoRepository.saveAll(List.of(medico1));

        // Crie uma doença
        Doenca doenca1 = new Doenca(1L,"Gripe","Febre, tosse, dor de cabeça", dg1);
        doencaRepository.saveAll(List.of(doenca1));

        // // Crie uma localização associada ao diagnóstico
        // Localizacao localizacao1 = new Localizacao(1L,"São Paulo", "SP","Rua Abc, 347", "Parque Regina", dg1);
        // LocalizacaoRepository.saveAll(List.of(localizacao1));
    }
}
