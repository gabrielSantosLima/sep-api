package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.AtaDto;
import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.FichaAvaliacaoEstagioDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.FichaDeAvaliacaoEstagioFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.TipoServico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FichaEstagioBuilderMessage implements IBuilderMessage<List<FichaDeAvaliacaoEstagioFields>, BancaDto>{

    private final Double MEDIA_APROVACAO = 6.0;
    private final String FUNCAO_DISCENTE_ESTAGIO = "Estagiário";
    private final String FUNCAO_DISCENTE_PROJETO = "Projeto";

    @Override
    public List<FichaDeAvaliacaoEstagioFields> retornarMensagem(BancaDto o) {
        List<FichaDeAvaliacaoEstagioFields> fichas = new ArrayList<>();

        String discente = retornarNomeDiscentes(o);
        String curso = retornarCurso(o);
        String anoFinalizacao = retornarAnoFinalizado(o);
        String funcaoDiscente = retornarFuncaoDiscentes(o);
        String dataEmissao = retornarDataEmissao();

        o.getAta().getFichasDeEstagio().forEach(ficha -> {
            Boolean eDiscente = ficha.getAvaliador().getFuncao() == FuncaoEstagio.DISCENTE;
            if(eDiscente) return;

            Double notaConhecimentos = ficha.getNotaConhecimento();
            Double notaOrganizacao = ficha.getNotaOrganizacao();
            Double notaAtividades = ficha.getNotaAtividades();
            Double notaApresentacao = ficha.getNotaApresentacao();
            Double soma = notaConhecimentos + notaOrganizacao + notaAtividades + notaApresentacao;

            String passou = verificarSePassou(soma >= MEDIA_APROVACAO);
            String naoPassou = verificarSePassou(soma < MEDIA_APROVACAO);

            String avaliador = ficha.getAvaliador().getNome();
            String funcaoAvaliador = retornarFuncaoAvaliador(ficha.getAvaliador());

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
        List<FichaDeAvaliacaoEstagioFields> fichas = new ArrayList<>();

        String discente = retornarNomeDiscentes(o);
        String curso = retornarCurso(o);
        String anoFinalizacao = retornarAnoFinalizado(o);
        String funcaoDiscente = retornarFuncaoDiscentes(o);
        String dataEmissao = retornarDataEmissao();

        o.getAta().getFichasDeEstagio().forEach(ficha -> {
            Boolean eDiscente = ficha.getAvaliador().getFuncao() == FuncaoEstagio.DISCENTE;
            if (eDiscente) return;

            String avaliador = ficha.getAvaliador().getNome();
            String funcaoAvaliador = retornarFuncaoAvaliador(ficha.getAvaliador());

            fichas.add(FichaDeAvaliacaoEstagioFields.builder()
                    .ano_finalizacao(anoFinalizacao)
                    .avaliador(avaliador)
                    .curso(curso)
                    .data_emissao(dataEmissao)
                    .discente(discente)
                    .funcao_avaliador(funcaoAvaliador)
                    .funcao_discente(funcaoDiscente)
                    .nao_passou(" ")
                    .nota_apresentacao(" ")
                    .nota_atividades(" ")
                    .nota_conhecimentos(" ")
                    .nota_organizacao(" ")
                    .passou(" ")
                    .soma(" ")
                    .build()
            );
        });

        return fichas;
    }

    private String retornarDataEmissao(){
        return FormatarData.porMascaraDataPadraoNomeCidade(new Date());
    }

    private List<UsuarioDto> retornarDiscentes(BancaDto o){
        return o.getParticipantes().stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.DISCENTE)
                .collect(Collectors.toList());
    }

    private String retornarNomeDiscentes(BancaDto o){
        String nomeDiscentes = "";

        List<UsuarioDto> discentes = retornarDiscentes(o);

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

    private String retornarFuncaoDiscentes(BancaDto o){
        return o.getEstagioPCCT().getTipo() == TipoServico.ESTAGIO?
                FUNCAO_DISCENTE_ESTAGIO :
                FUNCAO_DISCENTE_PROJETO;
    }

    private String retornarAnoFinalizado(BancaDto o){
        return retornarDiscentes(o).get(0)
                .getAnoFinalizacao();
    }

    private String retornarFuncaoAvaliador(UsuarioDto usuario){
        return usuario.getFuncao().name().toLowerCase();
    }

    private String retornarCurso(BancaDto o){
        return o.getCurso().retornarNomeCurso(o.getEstagioPCCT().getModalidadeCurso());
    }

    private String verificarSePassou(Boolean verificao){
        if(verificao) return "X";
        return " ";
    }
}
