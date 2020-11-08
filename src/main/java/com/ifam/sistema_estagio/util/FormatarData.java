package com.ifam.sistema_estagio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
    private static final String MASCARA_PADRAO_DATA = "dd/MM/yyyy";
    private static final String MASCARA_PADRAO_HORA = "hh:mm";

    public static String porMascara(String mascara, Date data){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mascara);
        String dataFormatada = simpleDateFormat.format(data);

        return dataFormatada;
    }

    public static String porMascaraPadraoHora(Date data){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MASCARA_PADRAO_HORA);
        String dataFormatada = simpleDateFormat.format(data);

        return dataFormatada;
    }

    public static String porMascaraPadraoData(Date data){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MASCARA_PADRAO_DATA);
        String dataFormatada = simpleDateFormat.format(data);

        return dataFormatada;
    }

}
