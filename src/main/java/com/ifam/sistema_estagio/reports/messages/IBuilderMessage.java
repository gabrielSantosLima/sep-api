package com.ifam.sistema_estagio.reports.messages;

public interface IBuilderMessage<E, O> {
    String CAMPO_VAZIO = " ";
    E retornarMensagem(O o);
    E retornarMensagemParaPreencher(O o);
}
