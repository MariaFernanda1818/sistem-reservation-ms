package com.gov.sistem.reservation.util.helper;

import com.gov.sistem.reservation.util.enums.InicialesCodEnum;

import java.util.Random;

public class Utilidades {

    public static String generarCodigo(InicialesCodEnum inicial){
        return new StringBuilder().append(inicial.getDescripcion()).append(1000 + new Random().nextInt(9000)).toString();
    }

}
