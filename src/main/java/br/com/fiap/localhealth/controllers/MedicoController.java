package br.com.fiap.localhealth.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import br.com.fiap.localhealth.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @Autowired
    DiagnosticoRepository diagnosticoRepository;

    @Autowired
    TokenService tokenService;


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        var medicos = (busca == null) ?
            repository.findAll(pageable):
            repository.findByCrmContaining(busca, pageable);

        return assembler.toModel(medicos.map(Medico::toEntityModel)); //HAL
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "o medico foi cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "os dados enviados são inválidos")
    })
    public ResponseEntity<EntityModel<Medico>> create(
            @RequestBody @Valid Medico medico,
            BindingResult result) {
        log.info("cadastrando medico: " + medico);
        repository.save(medico);
        medico.setDiagnostico(diagnosticoRepository.findById(medico.getDiagnostico().getId()).get());
        return ResponseEntity
            .created(medico.toEntityModel().getRequiredLink("self").toUri())
            .body(medico.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes do medico",
        description = "Retornar os dados do medico de acordo com o id informado no path"
    )
    public EntityModel<Medico> show(@PathVariable Long id) {
        log.info("buscando medico: " + id);
        return getMedico(id).toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Medico> destroy(@PathVariable Long id) {
        log.info("apagando medico: " + id);
        repository.delete(getMedico(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Medico>> update(
            @PathVariable Long id,
            @RequestBody @Valid Medico medico) {
        log.info("atualizando medico: " + id);
        getMedico(id);
        medico.setId(id);
        repository.save(medico);
        return ResponseEntity.ok(medico.toEntityModel());
    }

    private Medico getMedico(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RestNotFoundException("medico não encontrada"));
    }

}