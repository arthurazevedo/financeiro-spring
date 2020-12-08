package br.com.arthurvivere.financeiro.models.forms;

import br.com.arthurvivere.financeiro.models.Usuario;
import br.com.arthurvivere.financeiro.utils.annotations.IsValidNumber;

import javax.validation.constraints.Size;

public class AtualizaUsuarioForm {
    @Size(min = 5, max = 30)
    private String nome;
    @Size(min = 8, max = 11) @IsValidNumber
    private String telefone;
    @Size(min = 8, max = 100)
    private String email;
    private Character perfil;
    private Character status;

    public AtualizaUsuarioForm() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void atualizar(Usuario usuario) {
        if(this.nome != null && !(this.nome.trim().isEmpty())) usuario.setNome(this.nome);
        if(this.telefone != null && !(this.telefone.trim().isEmpty())) usuario.setTelefone(this.telefone);
        if(this.email != null && !(this.email.trim().isEmpty())) usuario.setEmail(this.email);
        if(this.perfil != null && (this.perfil.equals('A')||this.perfil.equals('O'))) usuario.setPerfil(this.perfil);
        if(this.status != null && (this.status.equals('A')||this.status.equals('C'))) usuario.setStatus(this.status);
    }
}
