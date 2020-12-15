package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import com.ifam.sistema_estagio.entity.NotaEstagio;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FichaAvaliacaoEstagioDto implements IObjetoDto<FichaDeAvaliacaoEstagio> {
    private String id;
    private Double notaConhecimento;
    private Double notaOrganizacao;
    private Double notaAtividades;
    private Double notaApresentacao;
    private UsuarioDto avaliador;
    private AtaDto ata;

    public FichaAvaliacaoEstagioDto(String id){
        this.id = id;
    }

    @Override
    public FichaDeAvaliacaoEstagio construirEntidade() {
        return FichaDeAvaliacaoEstagio.builder()
                .id(id)
                .ata(ata == null ? null: ata.construirEntidade())
                .professor(avaliador == null ? null: avaliador.construirProfessor())
                .nota(NotaEstagio.builder()
                        .notaApresentacao(notaApresentacao)
                        .notaAtividades(notaAtividades)
                        .notaConhecimento(notaConhecimento)
                        .notaOrganizacao(notaOrganizacao)
                        .build()
                )
                .build();
    }
}
