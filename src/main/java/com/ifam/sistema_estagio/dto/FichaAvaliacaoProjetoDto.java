package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FichaAvaliacaoProjetoDto implements IObjetoDto<FichaDeAvaliacaoProjeto> {
    private String id;
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
    private AtaDto ata;

    @Override
    public FichaDeAvaliacaoProjeto construirEntidade() {
        return FichaDeAvaliacaoProjeto.builder()
                .id(id)
                .ata(ata.construirEntidade())
                .professor(avaliador.construirProfessor())
                .notaDefesa(NotaProjetoDefesa.builder()
                        .notaAssunto(notaAssunto)
                        .notaClareza(notaClareza)
                        .notaLinguagem(notaLinguagem)
                        .notaRespostas(notaRespostas)
                        .notaSlide(notaSlide)
                        .notaTempo(notaTempo)
                        .build()
                )
                .notaTrabalho(NotaProjetoTrabalho.builder()
                        .notaABNT(notaABNT)
                        .notaApresentacao(notaApresentacao)
                        .notaConteudo(notaConteudo)
                        .notaDiagramas(notaDiagramas)
                        .notaFund(notaFund)
                        .notaMetodologia(notaMetodologia)
                        .notaResultados(notaResultados)
                        .build()
                )
                .build();
    }
}
