package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.util.FormatarData;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class CertificadoBuilderMessage implements IBuilderMessage<List<CertificadoFields>, BancaDto>{

    private static final String NOME_IFAM = "Instituto Federal de Educação, Ciência e Tecnologia do Amazonas - IFAM";

    @Override
    public List<CertificadoFields> retornarMensagem(BancaDto o) {
        val certificados = new ArrayList<CertificadoFields>();
        val nomeDiscentes = Utils.retornarNomeDiscentes(o);
        val curso = Utils.retornarCurso(o);
        val tipoBanca = Utils.retornarTipoBanca(o);

        o.getAvaliadores().forEach(avaliador -> {
            val data = FormatarData.porMascaraDataPadraoNomeCidade(o.getData());
            val mensagem = retornarMensagemCompleta(
                    avaliador,
                    nomeDiscentes,
                    curso,
                    tipoBanca
            );

            certificados.add(CertificadoFields.builder()
                    .data(data)
                    .mensagem(mensagem)
                    .build()
            );
        });

        o.getEstagioPCCT().getAlunos().forEach(aluno -> {
            val data = FormatarData.porMascaraDataPadraoNomeCidade(o.getData());
            val mensagem = retornarMensagemCompleta(
                    aluno,
                    nomeDiscentes,
                    curso,
                    tipoBanca
            );

            certificados.add(CertificadoFields.builder()
                    .data(data)
                    .mensagem(mensagem)
                    .build()
            );
        });

        val data = FormatarData.porMascaraDataPadraoNomeCidade(o.getData());
        val mensagem = retornarMensagemCompleta(
                o.getCoordenadora(),
                nomeDiscentes,
                curso,
                tipoBanca
        );

        certificados.add(CertificadoFields.builder()
                .data(data)
                .mensagem(mensagem)
                .build()
        );

        return certificados;
    }

    @Override
    public List<CertificadoFields> retornarMensagemParaPreencher(BancaDto o) {
        val certificados = new ArrayList<CertificadoFields>();
        val data = FormatarData.porMascaraDataPadraoNomeCidade(o.getData());
        val mensagem = retornarMensagemCompleta(
                UsuarioDto.builder().build(),
                CAMPO_VAZIO,
                CAMPO_VAZIO,
                CAMPO_VAZIO
        );
        certificados.add(CertificadoFields.builder()
                .data(data)
                .mensagem(mensagem)
                .build()
        );
        return certificados;
    }

    private String retornarMensagemCompleta(
            UsuarioDto o,
            String nomeDiscentes,
            String curso,
            String tipoBanca
    ){
        val papel = o.getFuncao().toString().toLowerCase();
        val nomeCompleto = o.getNome();
        return "Certificamos para os devidos fins de direito que o(a) Prof.<b>"+
                nomeCompleto +
                "</b> participou como " +
                papel +
                "(a) na banca de defesa do " +
                tipoBanca +
                " de <b>"+
                nomeDiscentes +
                "</b> do " +
                curso + "." +
                "<br>Coordenação do " +
                curso +
                " do " +
                NOME_IFAM;
    }
}
