package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.dto.FiltrosServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;

public interface IConsultaServicioService {

    RespuestaGeneralDTO consultaServiciosTipoServicio(Long tipoServicio);

    RespuestaGeneralDTO consultaServiciosReserva(String codigoReserva);

    RespuestaGeneralDTO consultaServiciosFiltros(FiltrosServicioDTO filtros);


}
