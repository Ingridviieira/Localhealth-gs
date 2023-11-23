package br.com.fiap.localhealth.controllers;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.localhealth.exception.RestNotFoundException;
import br.com.fiap.localhealth.models.Medico;
import br.com.fiap.localhealth.repository.DiagnosticoRepository;
import br.com.fiap.localhealth.repository.MedicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

   @GetMapping
    public List<Medico> index(){
        log.info("buscando todas os médicos");
        return medicoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Medico> create(
            @RequestBody @Valid Medico medico, 
            BindingResult result
        ){
        log.info("cadastrando medico: " + medico);
        medicoRepository.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);
    }

    @GetMapping("{id}")
    public ResponseEntity<Medico> show(@PathVariable Long id){
        log.info("buscando medico: " + id);
        return ResponseEntity.ok(getMedico(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Medico> destroy(@PathVariable Long id){
        log.info("apagando medico: " + id);
        medicoRepository.delete(getMedico(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Medico> update(
        @PathVariable Long id, 
        @RequestBody @Valid Medico medico
    ){
        log.info("atualizando medico: " + id);
        getMedico(id);
        medico.setId(id);
        medicoRepository.save(medico);
        return ResponseEntity.ok(medico);
    }

    private Medico getMedico(Long id) {
        return medicoRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("medico não encontrada"));
    }
    
}