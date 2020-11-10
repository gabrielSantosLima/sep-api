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
public class EstagioPCCTDto implements Serializable {
    private Integer id;
    private String titulo;
    private Integer cargaHoraria;
    private Boolean concluido;
    private String local;
    private String descricao;
    private TipoServico tipo;
}
