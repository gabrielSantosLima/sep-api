package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EstagioPCCTDto implements IObjetoDto<EstagioPCCT>{
    private String titulo;
    private Integer cargaHoraria;
    private Boolean concluido;
    private String local;
    private String descricao;
    private TipoServico tipo;
    private ModalidadeCurso modalidadeCurso;
    private List<UsuarioDto> participantes;

    @Override
    public EstagioPCCT construirEntidade() {
        Professor orientador = participantes.stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.ORIENTADOR)
                .findFirst()
                .get()
                .construirProfessor();

        return EstagioPCCT.builder()
                .cargaHoraria(cargaHoraria)
                .concluido(concluido)
                .local(local)
                .descricao(descricao)
                .tipo(tipo)
                .titulo(titulo)
                .responsavel(orientador)
                .build();
    }
}
