package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.val;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FichaEstagioBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoEstagioFields>, BancaDto>{

    private final Double MEDIA_APROVACAO = 6.0;
    private final String FUNCAO_DISCENTE_ESTAGIO = "Estagiário";
    private final String FUNCAO_DISCENTE_PROJETO = "Projeto";

    @Override
    public List<FichaDeAvaliacaoEstagioFields> retornarMensagem(BancaDto o) {
        val fichas = new ArrayList<FichaDeAvaliacaoEstagioFields>();
        val discente = Utils.retornarNomeDiscentes(o);
        val curso = Utils.retornarCurso(o);
        val anoFinalizacao = retornarAnoFinalizado(o);
        val funcaoDiscente = retornarFuncaoDiscentes(o);
        val dataEmissao = retornarDataEmissao();
        o.getAta().getFichasDeEstagio().forEach(ficha -> {
            val eDiscente = ficha.getAvaliador().getFuncao() == FuncaoEstagio.DISCENTE;
            if(eDiscente) return;

            val notaConhecimentos = ficha.getNotaConhecimento();
            val notaOrganizacao = ficha.getNotaOrganizacao();
            val notaAtividades = ficha.getNotaAtividades();
            val notaApresentacao = ficha.getNotaApresentacao();
            Double soma = notaConhecimentos + notaOrganizacao + notaAtividades + notaApresentacao;

            val passou = verificarSePassou(soma >= MEDIA_APROVACAO);
            val naoPassou = verificarSePassou(soma < MEDIA_APROVACAO);

            val avaliador = ficha.getAvaliador().getNome();
            val funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());

            fichas.add(FichaDeAvaliacaoEstagioFields.builder()
                    .ano_finalizacao(anoFinalizacao)
                    .avaliador(avaliador)
                    .curso(curso)
                    .data_emissao(dataEmissao)
                    .discente(discente)
                    .funcao_avaliador(funcaoAvaliador)
                    .funcao_discente(funcaoDiscente)
                    .nao_passou(naoPassou)
                    .nota_apresentacao(notaApresentacao.toString())
                    .nota_atividades(notaAtividades.toString())
                    .nota_conhecimentos(notaConhecimentos.toString())
                    .nota_organizacao(notaOrganizacao.toString())
                    .passou(passou)
                    .soma(soma.toString())
                    .build()
            );
        });
        return fichas;
    }

    @Override
    public List<FichaDeAvaliacaoEstagioFields> retornarMensagemParaPreencher(BancaDto o) {
        val fichas = new ArrayList<FichaDeAvaliacaoEstagioFields>();
        val discente = Utils.retornarNomeDiscentes(o);
        val curso = Utils.retornarCurso(o);
        val anoFinalizacao = retornarAnoFinalizado(o);
        val funcaoDiscente = retornarFuncaoDiscentes(o);
        val dataEmissao = retornarDataEmissao();
        o.getAta().getFichasDeEstagio().forEach(ficha -> {
            val eDiscente = ficha.getAvaliador().getFuncao() == FuncaoEstagio.DISCENTE;
            if (eDiscente) return;
            val avaliador = ficha.getAvaliador().getNome();
            val funcaoAvaliador = Utils.retornarFuncaoAvaliador(ficha.getAvaliador());
            fichas.add(FichaDeAvaliacaoEstagioFields.builder()
                    .ano_finalizacao(anoFinalizacao)
                    .avaliador(avaliador)
                    .curso(curso)
                    .data_emissao(dataEmissao)
                    .discente(discente)
                    .funcao_avaliador(funcaoAvaliador)
                    .funcao_discente(funcaoDiscente)
                    .nao_passou(CAMPO_VAZIO)
                    .nota_apresentacao(CAMPO_VAZIO)
                    .nota_atividades(CAMPO_VAZIO)
                    .nota_conhecimentos(CAMPO_VAZIO)
                    .nota_organizacao(CAMPO_VAZIO)
                    .passou(CAMPO_VAZIO)
                    .soma(CAMPO_VAZIO)
                    .build()
            );
        });
        return fichas;
    }

    private String retornarDataEmissao(){
        return FormatarData.porMascaraDataPadraoNomeCidade(new Date());
    }

    private String retornarFuncaoDiscentes(BancaDto o){
        return o.getEstagioPCCT().getTipo() == TipoServico.ESTAGIO?
                FUNCAO_DISCENTE_ESTAGIO :
                FUNCAO_DISCENTE_PROJETO;
    }

    private String retornarAnoFinalizado(BancaDto o){
        Date data = Utils.retornarDiscentes(o).get(0).getDataConclusao();
        return FormatarData.paraAno(data);
    }

    private String verificarSePassou(Boolean verificao){
        if(verificao) return "X";
        return CAMPO_VAZIO;
    }
}
