package com.gov.sistem.reservation.dto;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import lombok.Data;

import java.util.List;

@Data
public class InputReservaDTO {

    private ReservaDTO reservaDTO;
    private List<ServicioDTO> listServicios;

}
