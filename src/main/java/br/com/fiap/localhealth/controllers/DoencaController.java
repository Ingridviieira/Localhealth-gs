package br.com.fiap.localhealth.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import br.com.fiap.localhealth.models.Doenca;
import br.com.fiap.localhealth.models.Medico;
import br.com.fiap.localhealth.repository.DiagnosticoRepository;
import br.com.fiap.localhealth.repository.DoencaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/doencas")
@Slf4j
@SecurityRequirement(name = "bearer-key")
public class DoencaController {
    
    @Autowired
    private DoencaRepository doencaRepository;


    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        var doencas = (busca == null) ? 
            doencaRepository.findAll(pageable): 
            doencaRepository.findByNmDoencaContaining(busca, pageable);

        return assembler.toModel(doencas.map(Doenca::toEntityModel)); //HAL
    }

    @PostMapping
    public ResponseEntity<Doenca> create(
            @RequestBody @Valid Doenca doenca, 
            BindingResult result
        ){
        log.info("cadastrando doenca: " + doenca);
        doencaRepository.save(doenca);
        return ResponseEntity.status(HttpStatus.CREATED).body(doenca);
    }
    
    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes da doenca",
        description = "Retornar os dados da doenca de acordo com o id informado no path"
    )
    public EntityModel<Doenca> show(@PathVariable Long id) {
        log.info("buscando doenca: " + id);
        return getDoenca(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Doenca> destroy(@PathVariable Long id) {
        log.info("apagando doenca: " + id);
        doencaRepository.delete(getDoenca(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Doenca>> update(
            @PathVariable Long id,
            @RequestBody @Valid Doenca doenca) {
        log.info("atualizando doenca: " + id);
        getDoenca(id);
        doenca.setId(id);
        doencaRepository.save(doenca);
        return ResponseEntity.ok(doenca.toEntityModel());
    }

    private Doenca getDoenca(Long id) {
        return doencaRepository.findById(id).orElseThrow(
                () -> new RestNotFoundException("doença não encontrada"));
    }

}
