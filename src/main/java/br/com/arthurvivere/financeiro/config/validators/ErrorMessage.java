package br.com.arthurvivere.financeiro.config.validators;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private HttpStatus error;
    private String mensagem;

    public ErrorMessage(HttpStatus error, String mensagem) {
        this.error = error;
        this.mensagem = mensagem;
    }

    public HttpStatus getError() {
        return error;
    }

    public String getMensagem() {
        return mensagem;
    }
}
