package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.dto.FiltrosReservaDTO;
import com.gov.sistem.reservation.dto.FiltrosServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.service.IConsultaServicioService;
import com.gov.sistem.reservation.util.helper.ApiEndpointsConstants;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpointsConstants.BASE_SERVICIO)
@RequiredArgsConstructor
public class ServiciosController {

    private final IConsultaServicioService iConsultaServicioService;

    @GetMapping(ApiEndpointsConstants.SERVICIO_CONSULTA_TIPO_SERVICIO)
    public ResponseEntity<Object> consultaTipoServicio(@PathVariable("tipoServicio")Long tipoServicio){
        RespuestaGeneralDTO respuesta = iConsultaServicioService.consultaServiciosTipoServicio(tipoServicio);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }

    @GetMapping(ApiEndpointsConstants.SERVICIO_CONSULTA_CODIGO_RESERVA)
    public ResponseEntity<Object> consultaCodigoReserva(@PathVariable("codigoReserva")String codigoReserva){
        RespuestaGeneralDTO respuesta = iConsultaServicioService.consultaServiciosReserva(codigoReserva);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }

    @PostMapping(ApiEndpointsConstants.SERVICIO_CONSULTA_FILTROS)
    public ResponseEntity<Object> consultaFiltros(@RequestBody FiltrosServicioDTO filtrosServicioDTO){
        RespuestaGeneralDTO respuesta = iConsultaServicioService.consultaServiciosFiltros(filtrosServicioDTO);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }


}
