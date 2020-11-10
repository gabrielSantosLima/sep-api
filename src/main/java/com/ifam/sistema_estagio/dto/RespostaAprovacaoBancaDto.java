package com.ifam.sistema_estagio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class RespostaAprovacaoBancaDto implements Serializable {
    private Boolean aprovaBanca;
}
