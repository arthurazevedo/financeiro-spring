package br.com.arthurvivere.financeiro.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "livro_caixa", schema = "public")
public class LivroCaixa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Clientes cliente;
    private Date dataLancamento = Date.valueOf(LocalDate.now());;
    private String descricao;
    private char tipo;
    private BigDecimal valor;
    private BigDecimal saldo;

    public LivroCaixa(int id, Clientes cliente, Date dataLancamento, String descricao, char tipo, BigDecimal valor,
                      BigDecimal saldo) {
        this.id = id;
        this.cliente = cliente;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public LivroCaixa(Clientes cliente, String descricao, char tipo, BigDecimal valor, BigDecimal saldo) {
        this.cliente = cliente;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public LivroCaixa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
