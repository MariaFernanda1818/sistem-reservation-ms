package com.gov.sistem.reservation.util.enums;

import lombok.Getter;

@Getter
public enum InicialesCodEnum {

    RES("RES"),
    SER("SER"),
    CLIE("CLIE");

    private final String descripcion;

    InicialesCodEnum(String descripcion) {
        this.descripcion = descripcion;
    }

}
