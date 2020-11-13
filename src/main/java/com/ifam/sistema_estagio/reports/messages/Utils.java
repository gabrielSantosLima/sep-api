package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String retornarDataPadraoNomeCidade(BancaDto o){
        return FormatarData.porMascaraDataPadraoNomeCidade(o.getData());
    }

    public static String retornarTitulo(BancaDto o){
        return o.getEstagioPCCT().getTitulo();
    }

    public static String retornarNomeDiscentes(BancaDto o){
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

    public static List<UsuarioDto> retornarDiscentes(BancaDto o){
        return o.getParticipantes().stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.DISCENTE)
                .collect(Collectors.toList());
    }

    public static String retornarFuncaoAvaliador(UsuarioDto usuario){
        return usuario.getFuncao().name().toLowerCase();
    }

    public static String retornarCurso(BancaDto o){
        return o.getCurso().retornarNomeCurso(o.getEstagioPCCT().getModalidadeCurso());
    }

    public static String retornarTipoBanca(BancaDto o){
        return o.getEstagioPCCT().getTipo().getValor();
    }

    public static List<UsuarioDto> retornarAvaliadores(BancaDto o){
        return o.getParticipantes()
                .stream()
                .filter(participante -> participante.getFuncao() != FuncaoEstagio.DISCENTE)
                .collect(Collectors.toList());
    }
    public static UsuarioDto retornarOrientador(BancaDto o){
        return o.getParticipantes()
                .stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.ORIENTADOR)
                .findFirst()
                .get();
    }

    public static UsuarioDto retornarCoordenador(BancaDto o){
        return o.getParticipantes()
                .stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.COORDENADOR)
                .findFirst()
                .get();
    }

    public static String retornarNomeAvaliadoresComQuebraDeLinha(BancaDto o){
        String nomeAvaliadores = "";
        List<UsuarioDto> avaliadores = retornarAvaliadores(o);

        for(UsuarioDto avaliador: avaliadores) {
            nomeAvaliadores += avaliador.getNome() + " - " + avaliador.getFuncao().name().toLowerCase() + "<br>";
        };

        return nomeAvaliadores;
    }

    public static String retornarNomeAvaliadoresComVirgula(BancaDto o){
        String nomeAvaliadores = "";
        List<UsuarioDto> avaliadores = retornarAvaliadores(o);

        for(UsuarioDto avaliador: avaliadores) {
            Boolean naeEUltimo = avaliadores.indexOf(avaliador) != avaliadores.size() - 1;
            if(naeEUltimo){
                nomeAvaliadores += avaliador.getNome() + ",";
                continue;
            }
            nomeAvaliadores += avaliador.getNome();
        };

        return nomeAvaliadores;
    }

    public static String retornarNomeEFuncaoAvaliadoresComVirgula(BancaDto o){
        String nomeAvaliadores = "";
        List<UsuarioDto> avaliadores = retornarAvaliadores(o);
        for(UsuarioDto avaliador: avaliadores) {
            Boolean naeEUltimo = avaliadores.indexOf(avaliador) != avaliadores.size() - 1;
            nomeAvaliadores += avaliador.getNome() + " ("+ retornarFuncaoAvaliador(avaliador) +") ";
            if(naeEUltimo) nomeAvaliadores += ",";
        };
        return nomeAvaliadores;
    }
}
