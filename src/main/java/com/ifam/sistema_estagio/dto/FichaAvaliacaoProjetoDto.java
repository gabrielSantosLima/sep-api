package com.ifam.sistema_estagio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FichaAvaliacaoProjetoDto implements Serializable {
    private Double media;

    private Double notaSlide;
    private Double notaAssunto;
    private Double notaClareza;
    private Double notaLinguagem;
    private Double notaTempo;
    private Double notaRespostas;

    private Double notaApresentacao;
    private Double notaABNT;
    private Double notaMetodologia;
    private Double notaConteudo;
    private Double notaFund;
    private Double notaDiagramas;
    private Double notaResultados;

    private UsuarioDto avaliador;
}
