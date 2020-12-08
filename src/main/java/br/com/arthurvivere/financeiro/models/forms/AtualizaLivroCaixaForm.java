package br.com.arthurvivere.financeiro.models.forms;

import br.com.arthurvivere.financeiro.models.LivroCaixa;

import javax.validation.constraints.Size;

public class AtualizaLivroCaixaForm {
    @Size(min = 6, max = 50)
    private String descricao;

    public AtualizaLivroCaixaForm() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void atualizar(LivroCaixa livroCaixa) {
        if(this.descricao != null && !(this.descricao.isEmpty())) livroCaixa.setDescricao(this.descricao);
    }
}
