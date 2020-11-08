package com.ifam.sistema_estagio.util;

import java.util.Random;

public class ManipularNumerosHexadecimais {

    private final static Integer MAX_HEX_LENGTH = 20;
    private final static Integer PESO_PRIMEIRO_NUMERO = 100;
    private final static Integer PESO_SEGUNDO_NUMERO = 50;

    public static String numeroAleatorio(){
        Random random = new Random();

        Double numero1 = random.nextDouble() * PESO_PRIMEIRO_NUMERO;
        Double numero2 = random.nextDouble() * PESO_SEGUNDO_NUMERO;

        String hex = Double.toHexString(numero1) + "-" + Double.toHexString(numero2);

        if(hex.length() < MAX_HEX_LENGTH) return numeroAleatorio();

        return hex.substring(0, MAX_HEX_LENGTH);
    }
}
