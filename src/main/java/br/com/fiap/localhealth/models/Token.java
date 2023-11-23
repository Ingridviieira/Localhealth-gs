package br.com.fiap.localhealth.models;

public record Token(
    String token,
    String type,
    String prefix
) {}
