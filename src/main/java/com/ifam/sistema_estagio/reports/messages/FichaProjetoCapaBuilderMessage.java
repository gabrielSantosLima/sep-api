package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.*;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoCapaFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.TipoServico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FichaProjetoCapaBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoProjetoCapaFields>, BancaDto> {

    @Override
    public List<FichaDeAvaliacaoProjetoCapaFields> retornarMensagem(BancaDto o) {
        List<FichaDeAvaliacaoProjetoCapaFields> ficha = new ArrayList<>();
        String autor = Utils.retornarNomeDiscentes(o);
        String titulo = Utils.retornarTitulo(o);
        String data = Utils.retornarDataPadraoNomeCidade(o);

        String av1 = retornarNomeAvaliadorPorIndice(o, 0);
        String av2 = retornarNomeAvaliadorPorIndice(o, 1);
        String av3 = retornarNomeAvaliadorPorIndice(o, 2);

        Double notaDefesaAv1 = retornaNotasRelatorios(o, 0);
        Double notaDefesaAv2 = retornaNotasRelatorios(o, 1);
        Double notaDefesaAv3 = retornaNotasRelatorios(o, 2);

        Double notaRelatorioAv1 = retornaNotasDefesa(o, 0);
        Double notaRelatorioAv2 = retornaNotasDefesa(o, 1);
        Double notaRelatorioAv3 = retornaNotasDefesa(o, 2);

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
        List<FichaDeAvaliacaoProjetoCapaFields> ficha = new ArrayList<>();
        String autor = Utils.retornarNomeDiscentes(o);
        String titulo = Utils.retornarTitulo(o);
        String data = Utils.retornarDataPadraoNomeCidade(o);

        String av1 = retornarNomeAvaliadorPorIndice(o, 0);
        String av2 = retornarNomeAvaliadorPorIndice(o, 1);
        String av3 = retornarNomeAvaliadorPorIndice(o, 2);


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
        FichaAvaliacaoProjetoDto ficha = o.getAta()
                .getFichasDeProjeto()
                .get(indice);

        Double notaSlide = ficha.getNotaSlide();
        Double notaAssunto = ficha.getNotaAssunto();
        Double notaClareza = ficha.getNotaClareza();
        Double notaLinguagem = ficha.getNotaLinguagem();
        Double notaTempo = ficha.getNotaTempo();
        Double notaRespostas = ficha.getNotaRespostas();

        Double soma = notaAssunto + notaSlide + notaClareza + notaLinguagem + notaTempo + notaRespostas;
        return soma;
    }

    public Double retornaNotasDefesa(BancaDto o, Integer indice){
        FichaAvaliacaoProjetoDto ficha = o.getAta()
                .getFichasDeProjeto()
                .get(indice);

        Double notaApresentacao = ficha.getNotaApresentacao();
        Double notaABNT = ficha.getNotaABNT();
        Double notaMetodologia = ficha.getNotaMetodologia();
        Double notaConteudo = ficha.getNotaConteudo();
        Double notaFund = ficha.getNotaFund();
        Double notaDiagramas = ficha.getNotaDiagramas();
        Double notaResultados = ficha.getNotaResultados();

        Double soma = notaApresentacao + notaABNT + notaMetodologia + notaConteudo + notaFund + notaDiagramas + notaResultados;
        return soma;
    }
}