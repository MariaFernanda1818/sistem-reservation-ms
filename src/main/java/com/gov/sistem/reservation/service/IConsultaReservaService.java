package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.dto.FiltrosReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;


public interface IConsultaReservaService {

    RespuestaGeneralDTO buscarReservas(FiltrosReservaDTO filtrosReservas);

}
