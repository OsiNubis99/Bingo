package com.ucabnet;

public class Converter {
    public int getNumber (String datos) {
        String subdatos = datos.substring(0, 2);
        System.out.println(subdatos);
        if (subdatos.charAt(1) != '+')
            return Integer.parseInt(datos.substring(0, 2));
        else
            return 0;
    }

    public int getWinners (String datos) {
        if (datos.charAt(1) != '+') {
            String subdatos = datos.substring(3, 7);
            System.out.println(subdatos);
            return Integer.parseInt(subdatos);
        }
        else
            return 0;
    }

    public int getPlayer (String datos) {
        return Character.getNumericValue(datos.charAt(0));
    }

    public int getModo (String datos) {
        return Character.getNumericValue(datos.charAt(datos.length() - 3));
    }
}
