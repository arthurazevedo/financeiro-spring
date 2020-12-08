package br.com.arthurvivere.financeiro.models.forms;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.models.LivroCaixa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class LivroCaixaForm {
    @NotNull
    private int cliente_id;
    @Size(min = 6, max = 50) @NotNull
    private String descricao;
    @NotNull
    private char tipo;
    @NotNull
    private BigDecimal valor;

    public LivroCaixaForm() {
    }

    public int getCliente_id() {
        return cliente_id;
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

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LivroCaixa converter(Clientes cliente, BigDecimal saldo) {
        LivroCaixa livroCaixa = new LivroCaixa(cliente, this.descricao, this.tipo, this.valor, saldo);
        return livroCaixa;
    }
}
