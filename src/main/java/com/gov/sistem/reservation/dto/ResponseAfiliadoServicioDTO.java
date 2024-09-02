package com.gov.sistem.reservation.dto;

import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseAfiliadoServicioDTO {

    private List<ServicioDTO> servicios;

    private String codigoAfiliado;

    private String nombreAfiliado;

}
