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
}
