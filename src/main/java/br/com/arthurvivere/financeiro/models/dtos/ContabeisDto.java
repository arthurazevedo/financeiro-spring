package br.com.arthurvivere.financeiro.models.dtos;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.models.Contabil;
import br.com.arthurvivere.financeiro.models.LivroCaixa;

import java.util.List;
import java.util.stream.Collectors;

public class ContabeisDto {
    private int id;
    private String nome;
    private String cpf_cnpj;
    private String telefone;
    private List<Contabil> contabil;

    public ContabeisDto(List<LivroCaixa> livrosCaixa) {
        Clientes cliente = livrosCaixa.get(0).getCliente();
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf_cnpj = cliente.getCpf_cnpj();
        this.telefone = cliente.getTelefone();
        this.contabil = livrosCaixa.stream().map(Contabil::new).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<Contabil> getContabil() {
        return contabil;
    }
}
