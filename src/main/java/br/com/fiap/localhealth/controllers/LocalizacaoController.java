package br.com.fiap.localhealth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.localhealth.exception.RestNotFoundException;
import br.com.fiap.localhealth.models.Localizacao;
import br.com.fiap.localhealth.repository.LocalizacaoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/localizacao")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "T_Localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;


    @GetMapping
    public List<Localizacao> index(){
        log.info("buscando todas as localizaçãoes");
        return localizacaoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Localizacao> create(
            @RequestBody @Valid Localizacao localizacao, 
            BindingResult result
        ){
        log.info("cadastrando localização: " + localizacao);
        localizacaoRepository.save(localizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(localizacao);
    }

    @GetMapping("{id}")
    public ResponseEntity<Localizacao> show(@PathVariable Long id){
        log.info("buscando localização: " + id);
        return ResponseEntity.ok(getLocalizacao(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Localizacao> destroy(@PathVariable Long id){
        log.info("apagando localização: " + id);
        localizacaoRepository.delete(getLocalizacao(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Localizacao> update(
        @PathVariable Long id, 
        @RequestBody @Valid Localizacao localizacao
    ){
        log.info("atualizando localizacao: " + id);
        getLocalizacao(id);
        localizacao.setId(id);
        localizacaoRepository.save(localizacao);
        return ResponseEntity.ok(localizacao);
    }

    private Localizacao getLocalizacao(Long id) {
        return localizacaoRepository.findById(id).orElseThrow(
            () -> new RestNotFoundException("Localização não encontrada"));
    }
    
}