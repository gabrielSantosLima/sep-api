package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AtaDto implements Serializable {
    private Double mediaTotal;
    private String descricao;
    private TipoServico tipo;
    private BancaDto banca;
    private List<FichaAvaliacaoEstagioDto> fichasDeEstagio;
}
