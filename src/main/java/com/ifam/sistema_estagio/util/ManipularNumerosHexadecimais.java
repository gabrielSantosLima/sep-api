package com.ifam.sistema_estagio.util;

import java.util.Date;
import java.util.Random;

public class ManipularNumerosHexadecimais {

    private final static Integer TAMANHO_HEX_MAX = 20;
    private final static Integer PESO_PRIMEIRO_NUMERO = 100;
    private final static Integer PESO_SEGUNDO_NUMERO = 50;

    public static String numeroAleatorio(Integer tamanhoMax){
        Random random = new Random();
        Double numero1 = random.nextDouble() * PESO_PRIMEIRO_NUMERO;
        Double numero2 = random.nextDouble() * PESO_SEGUNDO_NUMERO;
        String hex = Double.toHexString(numero1) + Double.toHexString(numero2);

        if(hex.length() < tamanhoMax) return numeroAleatorio(tamanhoMax);

        String hexSubstring = hex.substring(0, tamanhoMax);
        StringBuilder hexFinal = new StringBuilder(hexSubstring);
        hexFinal.setCharAt(tamanhoMax / 2, '-');

        return hexFinal.toString() + FormatarData.paraAno(new Date());
    }

    public static String numeroAleatorio(){
        return numeroAleatorio(TAMANHO_HEX_MAX);
    }

}
