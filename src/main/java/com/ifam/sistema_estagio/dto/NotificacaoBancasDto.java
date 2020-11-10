package com.ifam.sistema_estagio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class NotificacaoBancasDto implements Serializable {
    private String dataNotificacao;
    private String idProcesso;
}
