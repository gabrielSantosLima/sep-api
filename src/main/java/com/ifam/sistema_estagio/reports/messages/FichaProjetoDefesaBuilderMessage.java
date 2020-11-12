package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.FichaAvaliacaoProjetoDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoCapaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoDefesaFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FichaProjetoDefesaBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoProjetoDefesaFields>, BancaDto> {

    @Override
    public List<FichaDeAvaliacaoProjetoDefesaFields> retornarMensagem(BancaDto o) {
        List<FichaDeAvaliacaoProjetoDefesaFields> fichas = new ArrayList<>();

        String autor = retornarNomeDiscentes(o);
        String titulo = o.getEstagioPCCT().getTitulo();
        String data = retornarData(o.getData());

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

            Double nota_qualidade = ficha.getNotaSlide();
            Double nota_conhecimento = ficha.getNotaAssunto();
            Double nota_clareza = ficha.getNotaClareza();
            Double nota_linguagem = ficha.getNotaLinguagem();
            Double nota_tempo = ficha.getNotaTempo();
            Double nota_resposta = ficha.getNotaRespostas();

            Double media = nota_clareza + nota_qualidade + nota_conhecimento + nota_linguagem + nota_tempo + nota_resposta;

            fichas.add(FichaDeAvaliacaoProjetoDefesaFields.builder()
                    .autor(autor)
                    .avaliador(nomeAvaliador)
                    .funcao_avaliador(funcaoAvaliador)
                    .data(data)
                    .nota_clareza(nota_clareza.toString())
                    .nota_conhecimento(nota_conhecimento.toString())
                    .nota_linguagem(nota_linguagem.toString())
                    .nota_qualidade(nota_qualidade.toString())
                    .nota_resposta(nota_resposta.toString())
                    .nota_tempo(nota_tempo.toString())
                    .titulo(titulo)
                    .total(media.toString())
                    .build()
            );
        });

        return fichas;
    }

    @Override
    public List<FichaDeAvaliacaoProjetoDefesaFields> retornarMensagemParaPreencher(BancaDto o) {
        List<FichaDeAvaliacaoProjetoDefesaFields> fichas = new ArrayList<>();

        String autor = retornarNomeDiscentes(o);
        String titulo = o.getEstagioPCCT().getTitulo();
        String data = retornarData(o.getData());

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

            fichas.add(FichaDeAvaliacaoProjetoDefesaFields.builder()
                    .autor(autor)
                    .avaliador(nomeAvaliador)
                    .funcao_avaliador(funcaoAvaliador)
                    .data(data)
                    .nota_clareza(" ")
                    .nota_conhecimento(" ")
                    .nota_linguagem(" ")
                    .nota_qualidade(" ")
                    .nota_resposta(" ")
                    .nota_tempo(" ")
                    .titulo(titulo)
                    .total(" ")
                    .build()
            );
        });

        return fichas;
    }

    private String retornarNomeDiscentes(BancaDto o){
        String nomeDiscentes = "";

        List<UsuarioDto> discentes = o.getParticipantes()
                .stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.DISCENTE)
                .collect(Collectors.toList());

        for(UsuarioDto discente: discentes) {
            Boolean naeEUltimo = discentes.indexOf(discente) != discentes.size() - 1;
            if(naeEUltimo){
                nomeDiscentes += discente.getNome() + ",";
                continue;
            }
            nomeDiscentes += discente.getNome();
        };

        return nomeDiscentes;
    }

    private String retornarFuncaoAvaliador(UsuarioDto usuario){
        return usuario.getFuncao().name().toLowerCase();
    }

    private String retornarData(Date data){
        String dataFormatada = FormatarData.porMascaraDataPadraoNomeCidade(data);
        return "Manaus(AM), "+ dataFormatada;
    }
}