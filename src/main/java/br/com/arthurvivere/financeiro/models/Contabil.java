package br.com.arthurvivere.financeiro.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Contabil {
    private Date dataLancamento;
    private String descricao;
    private char tipo;
    private BigDecimal valor;
    private BigDecimal saldo;

    public Contabil(LivroCaixa livroCaixa) {
        this.dataLancamento = livroCaixa.getDataLancamento();
        this.descricao = livroCaixa.getDescricao();
        this.tipo = livroCaixa.getTipo();
        this.valor = livroCaixa.getValor();
        this.saldo = livroCaixa.getSaldo();
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
