package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.util.FormatarData;

import java.util.ArrayList;
import java.util.List;

public class CertificadoBuilderMessage implements IBuilderMessage<List<CertificadoFields>, BancaDto>{

    private static final String NOME_IFAM = "Instituto Federal de Educação, Ciência e Tecnologia do Amazonas - IFAM";

    @Override
    public List<CertificadoFields> retornarMensagem(BancaDto o) {
        List<CertificadoFields> certificados = new ArrayList<>();
        String nomeDiscentes = Utils.retornarNomeDiscentes(o);
        String curso = Utils.retornarCurso(o);
        String tipoBanca = Utils.retornarTipoBanca(o);

        o.getParticipantes().forEach(participante -> {
            String data = FormatarData.porMascaraDataPadraoNomeCidade(o.getData());
            String mensagem = retornarMensagemCompleta(
                    participante,
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
        return certificados;
    }

    @Override
    public List<CertificadoFields> retornarMensagemParaPreencher(BancaDto o) {
        List<CertificadoFields> certificados = new ArrayList<>();

        String data = FormatarData.porMascaraDataPadraoNomeCidade(o.getData());
        String mensagem = retornarMensagemCompleta(
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
        String papel = o.getFuncao().toString().toLowerCase();
        String nomeCompleto = o.getNome();
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
