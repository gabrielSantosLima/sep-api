package com.ifam.sistema_estagio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
    private static final String MASCARA_DATA_PADRAO_NOME_CIDADE = "dd 'de' MMMM 'de' yyyy";
    private static final String MASCARA_DATA_PADRAO = "dd/MM/yyyy";
    private static final String MASCARA_HORA_PADRAO = "hh:mm";

    public static String porMascara(String mascara, Date data){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mascara);
        String dataFormatada = simpleDateFormat.format(data);

        return dataFormatada;
    }

    public static String porMascaraDataPadrao(Date data){
        return porMascara(MASCARA_DATA_PADRAO, data);
    }

    public static String porMascaraHoraPadrao(Date data){
        return porMascara(MASCARA_HORA_PADRAO, data);
    }

    public static String porMascaraDataPadraoNomeCidade(Date data){
        return porMascara(MASCARA_DATA_PADRAO_NOME_CIDADE, data);
    }
}
