package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoDefesaFields;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class FichaProjetoDefesaBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoProjetoDefesaFields>, BancaDto> {

    @Override
    public List<FichaDeAvaliacaoProjetoDefesaFields> retornarMensagem(BancaDto o) {
        val fichas = new ArrayList<FichaDeAvaliacaoProjetoDefesaFields>();

        val autor = Utils.retornarNomeDiscentes(o);
        val titulo = Utils.retornarTitulo(o);
        val data = Utils.retornarDataPadraoNomeCidade(o);

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            val funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());
            val nomeAvaliador = ficha.getAvaliador().getNome();

            val notaQualidade = ficha.getNotaSlide();
            val notaConhecimento = ficha.getNotaAssunto();
            val notaClareza = ficha.getNotaClareza();
            val notaLinguagem = ficha.getNotaLinguagem();
            val notaTempo = ficha.getNotaTempo();
            val notaResposta = ficha.getNotaRespostas();

            Double soma = notaQualidade +
                    notaConhecimento +
                    notaClareza +
                    notaLinguagem +
                    notaTempo +
                    notaResposta;

            fichas.add(FichaDeAvaliacaoProjetoDefesaFields.builder()
                    .autor(autor)
                    .data(data)
                    .titulo(titulo)
                    .avaliador(nomeAvaliador)
                    .funcao_avaliador(funcaoAvaliador)
                    .nota_qualidade(notaQualidade.toString())
                    .nota_conhecimento(notaConhecimento.toString())
                    .nota_clareza(notaClareza.toString())
                    .nota_linguagem(notaLinguagem.toString())
                    .nota_tempo(notaTempo.toString())
                    .nota_resposta(notaResposta.toString())
                    .total(soma.toString())
                    .build()
            );
        });
        return fichas;
    }

    @Override
    public List<FichaDeAvaliacaoProjetoDefesaFields> retornarMensagemParaPreencher(BancaDto o) {
        val fichas = new ArrayList<FichaDeAvaliacaoProjetoDefesaFields>();

        val autor = Utils.retornarNomeDiscentes(o);
        val titulo = Utils.retornarTitulo(o);
        val data = Utils.retornarDataPadraoNomeCidade(o);

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            val funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());
            val nomeAvaliador = ficha.getAvaliador().getNome();

            fichas.add(FichaDeAvaliacaoProjetoDefesaFields.builder()
                    .autor(autor)
                    .avaliador(nomeAvaliador)
                    .funcao_avaliador(funcaoAvaliador)
                    .data(data)
                    .nota_clareza(CAMPO_VAZIO)
                    .nota_conhecimento(CAMPO_VAZIO)
                    .nota_linguagem(CAMPO_VAZIO)
                    .nota_qualidade(CAMPO_VAZIO)
                    .nota_resposta(CAMPO_VAZIO)
                    .nota_tempo(CAMPO_VAZIO)
                    .titulo(titulo)
                    .total(CAMPO_VAZIO)
                    .build()
            );
        });
        return fichas;
    }
}