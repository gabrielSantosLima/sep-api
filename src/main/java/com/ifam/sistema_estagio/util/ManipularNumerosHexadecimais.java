package com.ifam.sistema_estagio.util;

import lombok.val;

import java.util.Date;
import java.util.Random;

public class ManipularNumerosHexadecimais {

    private final static Integer TAMANHO_HEX_MAX = 20;
    private final static Integer PESO_PRIMEIRO_NUMERO = 100;
    private final static Integer PESO_SEGUNDO_NUMERO = 50;
    public final static int TAMANHO_NUMERO_ALEATORIO = 28;

    public static String numeroAleatorio(Integer tamanhoMax){
        val random = new Random();
        val numero1 = random.nextDouble() * PESO_PRIMEIRO_NUMERO;
        val numero2 = random.nextDouble() * PESO_SEGUNDO_NUMERO;
        String hex = Double.toHexString(numero1) + Double.toHexString(numero2);
        if(hex.length() < tamanhoMax) return numeroAleatorio(tamanhoMax);
        val hexSubstring = hex.substring(0, tamanhoMax);
        val hexFinal = new StringBuilder(hexSubstring);
        hexFinal.setCharAt(tamanhoMax / 2, '-');
        val numeroAleatorio = hexFinal.toString() + FormatarData.paraDiaMesAno(new Date());
        return numeroAleatorio.substring(0, TAMANHO_NUMERO_ALEATORIO).replace(".", "");
    }

    public static String numeroAleatorio(){
        return numeroAleatorio(TAMANHO_HEX_MAX);
    }
}
