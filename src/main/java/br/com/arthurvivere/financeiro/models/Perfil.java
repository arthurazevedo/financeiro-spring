package br.com.arthurvivere.financeiro.models;

import org.springframework.security.core.GrantedAuthority;

public class Perfil implements GrantedAuthority {
    private String nome;

    public Perfil(char perfil) {
        this.nome = Character.toString(perfil);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
