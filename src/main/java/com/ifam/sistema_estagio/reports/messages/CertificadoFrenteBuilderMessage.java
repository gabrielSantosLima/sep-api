package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.CertificadoFields;
import com.ifam.sistema_estagio.reports.fields.FrenteCertificadoFields;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CertificadoFrenteBuilderMessage implements IBuilderMessage<List<FrenteCertificadoFields>, BancaDto> {

    @Override
    public List<FrenteCertificadoFields> retornarMensagem(BancaDto o) {
        List<FrenteCertificadoFields> certificados = new ArrayList<>();
        String autores = Utils.retornarNomeDiscentes(o);
        String data = Utils.retornarDataPadraoNomeCidade(o);
        String titulo = Utils.retornarTitulo(o);
        String participantes = Utils.retornarNomeAvaliadoresComQuebraDeLinha(o);

        o.getParticipantes().forEach(participante -> {
            certificados.add(FrenteCertificadoFields.builder()
                    .autores(autores)
                    .data(data)
                    .titulo(titulo)
                    .participantes(participantes)
                    .build()
            );
        });

        return certificados;
    }

    @Override
    public List<FrenteCertificadoFields> retornarMensagemParaPreencher(BancaDto o) {
        List<FrenteCertificadoFields> certificados = new ArrayList<>();
        String autores = Utils.retornarNomeDiscentes(o);
        String titulo = Utils.retornarTitulo(o);
        String participantes = Utils.retornarNomeAvaliadoresComQuebraDeLinha(o);

        o.getParticipantes().forEach(participante -> {
            certificados.add(FrenteCertificadoFields.builder()
                    .autores(autores)
                    .data(CAMPO_VAZIO)
                    .titulo(titulo)
                    .participantes(participantes)
                    .build()
            );
        });

        return certificados;
    }
}