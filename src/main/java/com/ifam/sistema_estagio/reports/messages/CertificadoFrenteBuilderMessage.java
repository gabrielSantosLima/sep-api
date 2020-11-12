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
        String autores = retornarNomeDiscentes(o);
        String data = retornarData(o.getData());
        String titulo = o.getEstagioPCCT().getTitulo();
        String participantes = retornarNomeAvaliadores(o);

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
        String autores = retornarNomeDiscentes(o);
        String titulo = o.getEstagioPCCT().getTitulo();
        String participantes = retornarNomeAvaliadores(o);

        o.getParticipantes().forEach(participante -> {
            certificados.add(FrenteCertificadoFields.builder()
                    .autores(autores)
                    .data(" ")
                    .titulo(titulo)
                    .participantes(participantes)
                    .build()
            );
        });

        return certificados;
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

    private String retornarNomeAvaliadores(BancaDto o){
        String nomeAvaliadores = "";
        List<UsuarioDto> avaliadores = o.getParticipantes()
                .stream()
                .filter(participante -> participante.getFuncao() != FuncaoEstagio.DISCENTE)
                .collect(Collectors.toList());

        for(UsuarioDto avaliador: avaliadores) {
            nomeAvaliadores += avaliador.getNome() + " - " + avaliador.getFuncao().name().toLowerCase() + "<br>";
        };

        return nomeAvaliadores;
    }

    private String retornarData(Date data){
        String dataFormatada = FormatarData.porMascaraDataPadraoNomeCidade(data);
        return "Manaus(AM), "+ dataFormatada;
    }
}