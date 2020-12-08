package br.com.arthurvivere.financeiro.models.dtos;

import br.com.arthurvivere.financeiro.models.LivroCaixa;

import java.math.BigDecimal;
import java.sql.Date;

public class LivroCaixaDto {
    private int id;
    private ClienteDto cliente;
    private Date dataLancamento;
    private String descricao;
    private char tipo;
    private BigDecimal valor;
    private BigDecimal saldo;

    public LivroCaixaDto(LivroCaixa livroCaixa) {
        this.id = livroCaixa.getId();
        this.cliente = new ClienteDto(livroCaixa.getCliente());
        this.dataLancamento = livroCaixa.getDataLancamento();
        this.descricao = livroCaixa.getDescricao();
        this.tipo = livroCaixa.getTipo();
        this.valor = livroCaixa.getValor();
        this.saldo = livroCaixa.getSaldo();
    }

    public int getId() {
        return id;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public char getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
