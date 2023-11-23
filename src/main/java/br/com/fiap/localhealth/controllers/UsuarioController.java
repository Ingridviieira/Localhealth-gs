package br.com.fiap.localhealth.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.localhealth.models.Credencial;
import br.com.fiap.localhealth.models.Token;
import br.com.fiap.localhealth.models.Usuario;
import br.com.fiap.localhealth.repository.UsuarioRepository;
import br.com.fiap.localhealth.service.TokenService;
import jakarta.validation.Valid;


@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/api/login")
    public Token login(@RequestBody Credencial credencial) {
        manager.authenticate(credencial.toAuthentication());
        return tokenService.generateToken(credencial);
    }
    
}
