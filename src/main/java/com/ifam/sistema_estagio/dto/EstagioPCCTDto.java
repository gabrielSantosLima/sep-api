package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.*;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstagioPCCTDto implements IObjetoDto<EstagioPCCT>{
    private String id;
    private String titulo;
    private Integer cargaHoraria;
    private Boolean concluido;
    private String local;
    private String descricao;
    private TipoServico tipo;
    private File anexo;
    private ModalidadeCurso modalidadeCurso;
    private UsuarioDto responsavel;
    private List<UsuarioDto> alunos;
    private List<BancaDto> bancas;

    public EstagioPCCTDto(String id){
        this.id = id;
    }

    @Override
    public EstagioPCCT construirEntidade() {
        return EstagioPCCT.builder()
                .id(id)
                .cargaHoraria(cargaHoraria)
                .concluido(concluido)
                .local(local)
                .descricao(descricao)
                .tipo(tipo)
                .titulo(titulo)
                .responsavel(responsavel == null ? null : responsavel.construirProfessor())
                .modalidadeCurso(modalidadeCurso)
                .anexo(anexo)
                .build();
    }
}
