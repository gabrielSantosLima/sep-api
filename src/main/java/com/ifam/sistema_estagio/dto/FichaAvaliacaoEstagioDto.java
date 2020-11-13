package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import com.ifam.sistema_estagio.entity.NotaEstagio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FichaAvaliacaoEstagioDto implements Serializable, IObjetoDto<FichaDeAvaliacaoEstagio> {
    private Double media;
    private Double notaConhecimento;
    private Double notaOrganizacao;
    private Double notaAtividades;
    private Double notaApresentacao;
    private UsuarioDto avaliador;
    private AtaDto ata;

    @Override
    public FichaDeAvaliacaoEstagio construirEntidade() {
        return FichaDeAvaliacaoEstagio.builder()
                .ata(ata.construirEntidade())
                .nota(NotaEstagio.builder()
                        .notaApresentacao(notaApresentacao)
                        .notaAtividades(notaAtividades)
                        .notaConhecimento(notaConhecimento)
                        .notaOrganizacao(notaOrganizacao)
                        .build())
                .professor(avaliador.construirProfessor())
                .build();
    }
}
