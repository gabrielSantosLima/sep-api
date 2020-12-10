package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.fields.FrenteCertificadoFields;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class CertificadoFrenteBuilderMessage implements IBuilderMessage<List<FrenteCertificadoFields>, BancaDto> {

    @Override
    public List<FrenteCertificadoFields> retornarMensagem(BancaDto o) {
        val certificados = new ArrayList<FrenteCertificadoFields>();
        val autores = Utils.retornarNomeDiscentes(o);
        val data = Utils.retornarDataPadraoNomeCidade(o);
        val titulo = Utils.retornarTitulo(o);
        val participantes = Utils.retornarNomeAvaliadoresComQuebraDeLinha(o);

        o.getAvaliadores().forEach(participante -> {
            certificados.add(FrenteCertificadoFields.builder()
                    .autores(autores)
                    .data(data)
                    .titulo(titulo)
                    .participantes(participantes)
                    .build()
            );
        });

        o.getEstagioPCCT().getAlunos().forEach(participante -> {
            certificados.add(FrenteCertificadoFields.builder()
                    .autores(autores)
                    .data(data)
                    .titulo(titulo)
                    .participantes(participantes)
                    .build()
            );
        });

        certificados.add(FrenteCertificadoFields.builder()
                .autores(autores)
                .data(data)
                .titulo(titulo)
                .participantes(participantes)
                .build()
        );
        return certificados;
    }

    @Override
    public List<FrenteCertificadoFields> retornarMensagemParaPreencher(BancaDto o) {
        val certificados = new ArrayList<FrenteCertificadoFields>();
        val autores = Utils.retornarNomeDiscentes(o);
        val titulo = Utils.retornarTitulo(o);
        val participantes = Utils.retornarNomeAvaliadoresComQuebraDeLinha(o);

        certificados.add(FrenteCertificadoFields.builder()
                .autores(autores)
                .data(CAMPO_VAZIO)
                .titulo(titulo)
                .participantes(participantes)
                .build()
        );
        return certificados;
    }
}