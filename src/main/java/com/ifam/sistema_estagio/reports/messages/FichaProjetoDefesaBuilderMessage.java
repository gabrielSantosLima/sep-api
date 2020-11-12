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

        String autor = Utils.retornarNomeDiscentes(o);
        String titulo = Utils.retornarTitulo(o);
        String data = Utils.retornarDataPadraoNomeCidade(o);

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

            Double notaQualidade = ficha.getNotaSlide();
            Double notaConhecimento = ficha.getNotaAssunto();
            Double notaClareza = ficha.getNotaClareza();
            Double notaLinguagem = ficha.getNotaLinguagem();
            Double notaTempo = ficha.getNotaTempo();
            Double notaResposta = ficha.getNotaRespostas();

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
        List<FichaDeAvaliacaoProjetoDefesaFields> fichas = new ArrayList<>();

        String autor = Utils.retornarNomeDiscentes(o);
        String titulo = Utils.retornarTitulo(o);
        String data = Utils.retornarDataPadraoNomeCidade(o);

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

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