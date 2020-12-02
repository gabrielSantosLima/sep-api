package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.*;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoCapaFields;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class FichaProjetoCapaBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoProjetoCapaFields>, BancaDto> {

    @Override
    public List<FichaDeAvaliacaoProjetoCapaFields> retornarMensagem(BancaDto o) {
        val ficha = new ArrayList<FichaDeAvaliacaoProjetoCapaFields>();
        val autor = Utils.retornarNomeDiscentes(o);
        val titulo = Utils.retornarTitulo(o);
        val data = Utils.retornarDataPadraoNomeCidade(o);

        val av1 = retornarNomeAvaliadorPorIndice(o, 0);
        val av2 = retornarNomeAvaliadorPorIndice(o, 1);
        val av3 = retornarNomeAvaliadorPorIndice(o, 2);

        val notaDefesaAv1 = retornaNotasRelatorios(o, 0);
        val notaDefesaAv2 = retornaNotasRelatorios(o, 1);
        val notaDefesaAv3 = retornaNotasRelatorios(o, 2);

        val notaRelatorioAv1 = retornaNotasDefesa(o, 0);
        val notaRelatorioAv2 = retornaNotasDefesa(o, 1);
        val notaRelatorioAv3 = retornaNotasDefesa(o, 2);

        Double mediaDefesa = (notaDefesaAv1 + notaDefesaAv2 + notaDefesaAv3) / 3;
        Double mediaRelatorio = (notaRelatorioAv1 + notaRelatorioAv2 + notaRelatorioAv3) / 3;
        Double media = (mediaDefesa + mediaRelatorio)/2;

        ficha.add(FichaDeAvaliacaoProjetoCapaFields.builder()
                .autor(autor)
                .titulo(titulo)
                .av1(av1)
                .av2(av2)
                .av3(av3)
                .data(data)
                .media(media.toString())
                .media_defesa(mediaDefesa.toString())
                .media_relatorio(mediaRelatorio.toString())
                .nota_defesa_av1(notaDefesaAv1.toString())
                .nota_defesa_av2(notaDefesaAv2.toString())
                .nota_defesa_av3(notaDefesaAv3.toString())
                .nota_relatorio_av1(notaRelatorioAv1.toString())
                .nota_relatorio_av2(notaRelatorioAv2.toString())
                .nota_relatorio_av3(notaRelatorioAv3.toString())
                .build()
        );
        return ficha;
    }

    @Override
    public List<FichaDeAvaliacaoProjetoCapaFields> retornarMensagemParaPreencher(BancaDto o) {
        val ficha = new ArrayList<FichaDeAvaliacaoProjetoCapaFields>();
        val autor = Utils.retornarNomeDiscentes(o);
        val titulo = Utils.retornarTitulo(o);
        val data = Utils.retornarDataPadraoNomeCidade(o);

        val av1 = retornarNomeAvaliadorPorIndice(o, 0);
        val av2 = retornarNomeAvaliadorPorIndice(o, 1);
        val av3 = retornarNomeAvaliadorPorIndice(o, 2);

        ficha.add(FichaDeAvaliacaoProjetoCapaFields.builder()
                .autor(autor)
                .titulo(titulo)
                .av1(av1)
                .av2(av2)
                .av3(av3)
                .data(data)
                .media(CAMPO_VAZIO)
                .media_defesa(CAMPO_VAZIO)
                .media_relatorio(CAMPO_VAZIO)
                .nota_defesa_av1(CAMPO_VAZIO)
                .nota_defesa_av2(CAMPO_VAZIO)
                .nota_defesa_av3(CAMPO_VAZIO)
                .nota_relatorio_av1(CAMPO_VAZIO)
                .nota_relatorio_av2(CAMPO_VAZIO)
                .nota_relatorio_av3(CAMPO_VAZIO)
                .build()
        );

        return ficha;
    }

    public String retornarNomeAvaliadorPorIndice(BancaDto o, Integer indice){
        return o.getAta().getFichasDeProjeto().get(indice).getAvaliador().getNome();
    }

    public Double retornaNotasRelatorios(BancaDto o, Integer indice){
        val ficha = o.getAta()
                .getFichasDeProjeto()
                .get(indice);
        val notaSlide = ficha.getNotaSlide();
        val notaAssunto = ficha.getNotaAssunto();
        val notaClareza = ficha.getNotaClareza();
        val notaLinguagem = ficha.getNotaLinguagem();
        val notaTempo = ficha.getNotaTempo();
        val notaRespostas = ficha.getNotaRespostas();
        Double soma = notaAssunto + notaSlide + notaClareza + notaLinguagem + notaTempo + notaRespostas;
        return soma;
    }

    public Double retornaNotasDefesa(BancaDto o, Integer indice){
        FichaAvaliacaoProjetoDto ficha = o.getAta()
                .getFichasDeProjeto()
                .get(indice);
        val notaApresentacao = ficha.getNotaApresentacao();
        val notaABNT = ficha.getNotaABNT();
        val notaMetodologia = ficha.getNotaMetodologia();
        val notaConteudo = ficha.getNotaConteudo();
        val notaFund = ficha.getNotaFund();
        val notaDiagramas = ficha.getNotaDiagramas();
        val notaResultados = ficha.getNotaResultados();
        Double soma = notaApresentacao + notaABNT + notaMetodologia + notaConteudo + notaFund + notaDiagramas + notaResultados;
        return soma;
    }
}