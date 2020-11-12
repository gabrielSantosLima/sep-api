package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EstagioPCCTDto implements Serializable {
    private String titulo;
    private Integer cargaHoraria;
    private Boolean concluido;
    private String local;
    private String descricao;
    private TipoServico tipo;
    private ModalidadeCurso modalidadeCurso;
}
