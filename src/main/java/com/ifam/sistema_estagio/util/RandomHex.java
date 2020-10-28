package com.ifam.sistema_estagio.util;

import org.apache.tomcat.util.buf.HexUtils;
import java.util.Random;

public class RandomHex {

    private final static Integer MAX_HEX_LENGTH = 20;

    public static String generateRandomHex(){
        Random random = new Random();

        Double numero1 = random.nextDouble() * 100;
        Double numero2 = random.nextDouble() * 100;

        String hex = Double.toHexString(numero1) +
                Double.toHexString(numero2);

        if(hex.length() < MAX_HEX_LENGTH) return generateRandomHex();

        return hex.substring(0, MAX_HEX_LENGTH);
    }
}
