package br.com.arthurvivere.financeiro.models.forms;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.utils.annotations.IsValidNumber;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteForm {
    @Size(min = 4, max = 30) @NotNull @NotEmpty
    private String nome;
    @Size(min = 11, max = 14) @NotNull @NotEmpty @IsValidNumber
    private String cpf_cnpj;
    @Size(min = 4, max = 50) @NotNull @NotEmpty
    private String logradouro;
    @Size(min = 4, max = 40) @NotNull @NotEmpty
    private String cidade;
    @Size(min = 2, max = 2) @NotNull @NotEmpty
    private String uf;
    @Size(min = 8, max = 8) @NotNull @NotEmpty @IsValidNumber
    private String cep;
    @Size(min = 8, max = 11) @IsValidNumber
    private String telefone;
    @Size(min = 8, max = 100)
    private String email;

    public ClienteForm() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Clientes converter() {
        String cpf_cnpj = this.cpf_cnpj.replace(".", "").replace("-", "")
                .replace(" ", "").trim();

        Clientes cliente = new Clientes(this.nome, cpf_cnpj, this.logradouro,
                this.cidade, this.uf, this.cep, this.telefone, this.email);

        return cliente;
    }
}
