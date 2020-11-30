package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
    private List<UsuarioDto> participantes;

    @Override
    public EstagioPCCT construirEntidade() {
        Professor responsavel = participantes.stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.ORIENTADOR)
                .findFirst()
                .get()
                .construirProfessor();

        List<Aluno> alunos = participantes.stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.DISCENTE)
                .map(participante -> participante.construirAluno())
                .collect(Collectors.toList());

        return EstagioPCCT.builder()
                .id(id)
                .cargaHoraria(cargaHoraria)
                .concluido(concluido)
                .local(local)
                .descricao(descricao)
                .tipo(tipo)
                .titulo(titulo)
                .responsavel(responsavel)
                .alunos(alunos)
                .modalidadeCurso(modalidadeCurso)
                .anexo(anexo)
                .build();
    }
}
