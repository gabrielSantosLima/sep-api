package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoRelatorioFields;

import java.util.ArrayList;
import java.util.List;

public class FichaProjetoRelatorioBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoProjetoRelatorioFields>, BancaDto> {

    @Override
    public List<FichaDeAvaliacaoProjetoRelatorioFields> retornarMensagem(BancaDto o) {
        List<FichaDeAvaliacaoProjetoRelatorioFields> fichas = new ArrayList<>();

        String autor = Utils.retornarNomeDiscentes(o);
        String titulo = Utils.retornarTitulo(o);
        String data = Utils.retornarDataPadraoNomeCidade(o);

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

            Double notaApresentacao = ficha.getNotaApresentacao();
            Double notaABNT = ficha.getNotaABNT();
            Double notaMetodologia = ficha.getNotaMetodologia();
            Double notaConteudo = ficha.getNotaConteudo();
            Double notaFund = ficha.getNotaFund();
            Double notaDiagramas = ficha.getNotaDiagramas();
            Double notaResultados = ficha.getNotaResultados();

            Double media = notaApresentacao +
                    notaABNT +
                    notaMetodologia +
                    notaConteudo +
                    notaFund +
                    notaDiagramas +
                    notaResultados;

            fichas.add(FichaDeAvaliacaoProjetoRelatorioFields.builder()
                    .autor(autor)
                    .avaliador(nomeAvaliador)
                    .funcao_avaliador(funcaoAvaliador)
                    .data(data)
                    .titulo(titulo)
                    .nota_abnt(notaABNT.toString())
                    .nota_apresentacao(notaApresentacao.toString())
                    .nota_documentacao(notaDiagramas.toString())
                    .nota_fundamentacao(notaFund.toString())
                    .nota_metodologia(notaMetodologia.toString())
                    .nota_qualidade(notaConteudo.toString())
                    .nota_resultados(notaResultados.toString())
                    .total(media.toString())
                    .build()
            );
        });
        return fichas;
    }

    @Override
    public List<FichaDeAvaliacaoProjetoRelatorioFields> retornarMensagemParaPreencher(BancaDto o) {
        List<FichaDeAvaliacaoProjetoRelatorioFields> fichas = new ArrayList<>();

        String autor = Utils.retornarNomeDiscentes(o);
        String titulo = Utils.retornarTitulo(o);
        String data = Utils.retornarDataPadraoNomeCidade(o);

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

            fichas.add(FichaDeAvaliacaoProjetoRelatorioFields.builder()
                    .autor(autor)
                    .avaliador(nomeAvaliador)
                    .funcao_avaliador(funcaoAvaliador)
                    .data(data)
                    .titulo(titulo)
                    .nota_abnt(CAMPO_VAZIO)
                    .nota_apresentacao(CAMPO_VAZIO)
                    .nota_documentacao(CAMPO_VAZIO)
                    .nota_fundamentacao(CAMPO_VAZIO)
                    .nota_metodologia(CAMPO_VAZIO)
                    .nota_qualidade(CAMPO_VAZIO)
                    .nota_resultados(CAMPO_VAZIO)
                    .total(CAMPO_VAZIO)
                    .build()
            );
        });
        return fichas;
    }
}