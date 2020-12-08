package br.com.arthurvivere.financeiro.models.forms;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.utils.annotations.IsValidNumber;

import javax.validation.constraints.Size;

public class AtualizaClienteForm {
    @Size(min = 4, max = 30)
    private String nome;
    @Size(min = 11, max = 14) @IsValidNumber
    private String cpf_cnpj;
    @Size(min = 4, max = 50)
    private String logradouro;
    @Size(min = 4, max = 40)
    private String cidade;
    @Size(min = 2, max = 2)
    private String uf;
    @Size(min = 8, max = 8) @IsValidNumber
    private String cep;
    @Size(min = 8, max = 11) @IsValidNumber
    private String telefone;
    @Size(min = 8, max = 100)
    private String email;

    public AtualizaClienteForm() {
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

    public void setNome(String nome) {
        this.nome = nome;
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

    public void atualizar(Clientes cliente) {
        if(this.nome != null && !(this.nome.trim().isEmpty())) cliente.setNome(this.nome);
        if(cpf_cnpj != null){
            String cpf_cnpj = this.cpf_cnpj.replace("-", "").replace(".", "").trim();
            if(!cpf_cnpj.isEmpty()) cliente.setCpf_cnpj(cpf_cnpj);
        }
        if(this.logradouro != null && !(this.logradouro.trim().isEmpty())) cliente.setLogradouro(this.logradouro);
        if(this.cidade != null && !(this.cidade.trim().isEmpty())) cliente.setCidade(this.cidade);
        if(this.uf != null && !(this.uf.trim().isEmpty())) cliente.setUf(this.uf);
        if(this.cep != null && !(this.cep.trim().isEmpty())) cliente.setCep(this.cep);
        if(this.telefone != null && !(this.telefone.trim().isEmpty())) cliente.setTelefone(this.telefone);
        if(this.email != null && !(this.email.trim().isEmpty())) cliente.setEmail(this.email);
    }
}
