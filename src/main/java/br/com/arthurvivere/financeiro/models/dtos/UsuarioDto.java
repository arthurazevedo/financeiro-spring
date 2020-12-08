package br.com.arthurvivere.financeiro.models.dtos;

import br.com.arthurvivere.financeiro.models.Usuario;

import java.sql.Date;

public class UsuarioDto {
    private int id;
    private Date dataCadastro;
    private String nome;
    private String login;
    private String telefone;
    private String email;
    private char perfil;
    private char status;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.dataCadastro = usuario.getDataCadastro();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.perfil = usuario.getPerfil();
        this.status = usuario.getStatus();
    }

    public UsuarioDto() {
    }

    public int getId() {
        return id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public char getPerfil() {
        return perfil;
    }

    public char getStatus() {
        return status;
    }
}
