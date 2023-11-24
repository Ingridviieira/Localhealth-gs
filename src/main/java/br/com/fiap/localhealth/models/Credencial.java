package br.com.fiap.localhealth.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record Credencial(String crm, String senha) {

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(crm, senha);
    }
    
}
