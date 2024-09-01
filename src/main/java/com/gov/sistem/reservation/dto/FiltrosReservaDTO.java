package com.gov.sistem.reservation.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FiltrosReservaDTO {

    private LocalDate fecha;
    private String nombreServicio;
    private String codigoCliente;
}
