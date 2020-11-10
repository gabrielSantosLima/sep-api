package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AtaDto implements Serializable {
    private Integer mediaTotal;
    private String descricao;
    private TipoServico tipo;
    private BancaDto banca;
}
