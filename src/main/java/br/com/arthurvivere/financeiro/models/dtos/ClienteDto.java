package br.com.arthurvivere.financeiro.models.dtos;

import br.com.arthurvivere.financeiro.models.Clientes;

import java.sql.Date;

public class ClienteDto {
    private int id;
    private Date dataCadastro;
    private String nome;
    private String cpf_cnpj;
    private String logradouro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;

    public ClienteDto(Clientes cliente) {
        this.id = cliente.getId();
        this.dataCadastro = cliente.getDataCadastro();
        this.nome = cliente.getNome();
        this.cpf_cnpj = cliente.getCpf_cnpj();
        this.logradouro = cliente.getLogradouro();
        this.cidade = cliente.getCidade();
        this.uf = cliente.getUf();
        this.cep = cliente.getCep();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
    }

    public ClienteDto() {

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
}
