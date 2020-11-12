package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CertificadoBuilderMessage implements IBuilderMessage<List<CertificadoFields>, BancaDto>{

    private static final String NOME_IFAM = "Instituto Federal de Educação, Ciência e Tecnologia do Amazonas - IFAM";

    @Override
    public List<CertificadoFields> retornarMensagem(BancaDto o) {
        List<CertificadoFields> certificados = new ArrayList<>();
        String nomeDiscentes = retornarNomeDiscentes(o);
        String curso = retornarCurso(o);
        String tipoBanca = retornarTipoBanca(o);

        o.getParticipantes().forEach(participante -> {
            String data = retornarData(o.getData());
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

        String data = retornarData(o.getData());
        String mensagem = retornarMensagemCompleta(
                UsuarioDto.builder().build(),
                "                            ",
                "                            ",
                "                            "
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

    private String retornarData(Date data){
        String dataFormatada = FormatarData.porMascaraDataPadraoNomeCidade(data);
        return "Manaus(AM), "+ dataFormatada;
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

    private String retornarCurso(BancaDto o){
        return o.getCurso().retornarNomeCurso(o.getEstagioPCCT().getModalidadeCurso());
    }

    private String retornarTipoBanca(BancaDto o){
        return o.getEstagioPCCT().getTipo().getValor();
    }
}
