package br.com.arthurvivere.financeiro.models.forms;

import br.com.arthurvivere.financeiro.models.Usuario;
import br.com.arthurvivere.financeiro.utils.annotations.IsValidNumber;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioForm {
    @NotNull @NotEmpty @Size(min = 5, max = 30)
    private String nome;

    @NotNull @NotEmpty @Size(min = 5, max = 15)
    private String login;

    @NotNull @NotEmpty @Size(min = 4, max = 10)
    private String senha;

    @Size(min = 8, max = 11) @IsValidNumber
    private String telefone;

    @Size(min = 8, max = 100)
    private String email;

    @NotNull
    private Character perfil;

    private Character status;

    public UsuarioForm() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Character getPerfil() {
        return perfil;
    }

    public Character getStatus() {
        return status;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPerfil(Character perfil) {
        this.perfil = perfil;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Usuario converter() {
        Usuario usuario = new Usuario(this.nome, this.login, this.senha, this.telefone, this.email);

        if(this.perfil.equals('A')||this.perfil.equals('O')) usuario.setPerfil(this.perfil);
        else usuario.setPerfil('O');
        if(this.status != null && (this.status.equals('A')||this.status.equals('C')))
            usuario.setStatus(this.status);
        else usuario.setStatus('A');
        return usuario;
    }

}
