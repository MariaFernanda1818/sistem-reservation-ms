package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;

import java.util.List;

public interface IModificarReservaService {


    RespuestaGeneralDTO modificarReserva(ReservaDTO reservaDTO);

}
