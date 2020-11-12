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
public class FichaAvaliacaoEstagioDto implements Serializable {
    private Double media;
    private Double notaConhecimento;
    private Double notaOrganizacao;
    private Double notaAtividades;
    private Double notaApresentacao;
    private UsuarioDto avaliador;
    private AtaDto ata;
}
