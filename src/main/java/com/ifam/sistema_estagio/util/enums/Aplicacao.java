package com.ifam.sistema_estagio.util.enums;

import java.util.Arrays;

public enum Aplicacao {
    BASE_URL("http://localhost:8080");

    private String valor;

    Aplicacao(String valor ){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
