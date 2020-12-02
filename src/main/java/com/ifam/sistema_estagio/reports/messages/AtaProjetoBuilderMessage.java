package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.FichaAvaliacaoProjetoDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.AtaProjetoFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AtaProjetoBuilderMessage implements IBuilderMessage<List<AtaProjetoFields>, BancaDto> {

    @Override
    public List<AtaProjetoFields> retornarMensagem(BancaDto o) {
        val atas = new ArrayList<AtaProjetoFields>();
        val data = Utils.retornarDataPadraoNomeCidade(o);
        val membro1 = retornarColaboradoresProjeto(o).get(0).getNome();
        val membro2 = retornarColaboradoresProjeto(o).get(1).getNome();
        val presidente = Utils.retornarOrientador(o).getNome();
        val titulo = Utils.retornarTitulo(o);
        val media = o.getAta().getMediaTotal().toString();

        Utils.retornarDiscentes(o).forEach(discente -> {
            val mensagem = retornarMensagemCompleta(o, discente);
            val curso = Utils.retornarCurso(o).toUpperCase();

            atas.add(AtaProjetoFields.builder()
                    .data(data)
                    .media(media)
                    .membro_1(membro1)
                    .membro_2(membro2)
                    .mensagem(mensagem)
                    .curso(curso)
                    .presidente(presidente)
                    .titulo(titulo)
                    .mediaExtenso(media)
                    .build());
        });

        return atas;
    }

    @Override
    public List<AtaProjetoFields> retornarMensagemParaPreencher(BancaDto o) {
        return new ArrayList<>();
    }


    private String retornarMensagemCompleta(BancaDto o, UsuarioDto discente){
        return "No dia <b>" +
                FormatarData.porMascaraDataPadraoSemCidade(o.getData()) +
                "</b>, no " +
                o.getEstagioPCCT().getLocal() +
                " deste IFAM, no horário de <b>" +
                FormatarData.porMascaraHoraPadrao(o.getHoraInicio()) +
                "</b> às <b>" +
                FormatarData.porMascaraHoraPadrao(o.getHoraFinalizado()) +
                ", estiveram presentes os professores <b>" +
                Utils.retornarNomeEFuncaoAvaliadoresComVirgula(o) +
                "</b>, para juntos avaliarem o projeto do aluno(a), da turma <b>" +
                discente.getTurma() +
                " do ano de <b>" +
                FormatarData.paraAno(o.getData()) +
                "</b>, que teve como professor(a) orientador(a) <b>" +
                Utils.retornarOrientador(o).getNome() +
                "</b>. O(A) aluno(a) " +
                discente.getNome() +
                " expôs seus conhecimentos adquiridos ao longo do curso, de forma clara e objetiva, " +
                "levando os avaliadores a concluir que o projeto está elaborado de acordo com as normas" +
                "técnicas pertinentes e sendo atribuída pela banca avaliadora: ";
    }

    private List<UsuarioDto> retornarColaboradoresProjeto(BancaDto o){
        val colaboradores = new ArrayList<UsuarioDto>();
        val fichas = o.getAta()
                .getFichasDeProjeto()
                .stream()
                .filter(ficha -> ficha.getAvaliador().getFuncao() == FuncaoEstagio.COLABORADOR)
                .collect(Collectors.toList());
        fichas.forEach(ficha -> colaboradores.add(ficha.getAvaliador()));
        return colaboradores;
    }
}
