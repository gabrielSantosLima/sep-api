package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.reports.fields.AtaProjetoFields;

import java.util.List;

public class AtaProjetoBuilderMessage implements IBuilderMessage<List<AtaProjetoFields>, BancaDto> {

    @Override
    public List<AtaProjetoFields> retornarMensagem(BancaDto o) {
        return null;
    }

    @Override
    public List<AtaProjetoFields> retornarMensagemParaPreencher(BancaDto o) {
        return null;
    }
}