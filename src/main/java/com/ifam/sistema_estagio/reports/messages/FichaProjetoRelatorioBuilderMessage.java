package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoDefesaFields;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoProjetoRelatorioFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FichaProjetoRelatorioBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoProjetoRelatorioFields>, BancaDto> {

    @Override
    public List<FichaDeAvaliacaoProjetoRelatorioFields> retornarMensagem(BancaDto o) {
        List<FichaDeAvaliacaoProjetoRelatorioFields> fichas = new ArrayList<>();

        String autor = retornarNomeDiscentes(o);
        String titulo = o.getEstagioPCCT().getTitulo();
        String data = retornarData(o.getData());

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

            Double notaApresentacao = ficha.getNotaApresentacao();
            Double notaABNT = ficha.getNotaABNT();
            Double notaMetodologia = ficha.getNotaMetodologia();
            Double notaConteudo = ficha.getNotaConteudo();
            Double notaFund = ficha.getNotaFund();
            Double notaDiagramas = ficha.getNotaDiagramas();
            Double notaResultados = ficha.getNotaResultados();

            Double media = notaApresentacao + notaABNT + notaMetodologia + notaConteudo + notaFund + notaDiagramas + notaResultados;

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

        String autor = retornarNomeDiscentes(o);
        String titulo = o.getEstagioPCCT().getTitulo();
        String data = retornarData(o.getData());

        o.getAta().getFichasDeProjeto().forEach(ficha -> {
            String funcaoAvaliador = retornarFuncaoAvaliador(ficha.getAvaliador());
            String nomeAvaliador = ficha.getAvaliador().getNome();

            fichas.add(FichaDeAvaliacaoProjetoRelatorioFields.builder()
                    .autor(autor)
                    .avaliador(nomeAvaliador)
                    .funcao_avaliador(funcaoAvaliador)
                    .data(data)
                    .titulo(titulo)
                    .nota_abnt(" ")
                    .nota_apresentacao(" ")
                    .nota_documentacao(" ")
                    .nota_fundamentacao(" ")
                    .nota_metodologia(" ")
                    .nota_qualidade(" ")
                    .nota_resultados(" ")
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