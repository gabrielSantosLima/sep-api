package com.ifam.sistema_estagio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
    private static final String MASCARA_DATA_PADRAO_NOME_CIDADE = "'Manaus(AM),' dd 'de' MMMM 'de' yyyy";
    private static final String MASCARA_DATA_PADRAO_SEM_CIDADE = "dd 'de' MMMM 'de' yyyy";
    private static final String MASCARA_DATA_PADRAO = "dd/MM/yyyy";
    private static final String MASCARA_HORA_PADRAO = "hh:mm";
    private static final String MASCARA_ANO = "yyyy";

    private static String porMascara(String mascara, Date data){
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

    public static String porMascaraDataPadraoSemCidade(Date data){
        return porMascara(MASCARA_DATA_PADRAO_SEM_CIDADE, data);
    }

    public static String porMascaraAno(Date data){
        return porMascara(MASCARA_ANO, data);
    }
}
