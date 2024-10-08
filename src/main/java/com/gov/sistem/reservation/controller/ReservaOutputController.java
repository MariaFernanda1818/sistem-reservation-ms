package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.dto.FiltrosReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.service.IConsultaReservaService;
import com.gov.sistem.reservation.util.helper.ApiEndpointsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpointsConstants.BASE_OUTPUT)
@RequiredArgsConstructor
public class ReservaOutputController {

    private final IConsultaReservaService iConsultaReservaService;

    @PostMapping(ApiEndpointsConstants.OUTPUT_CONSULTA_RESERVAS_FILTRO)
    public ResponseEntity<Object> consultarReservasFiltro(@RequestBody FiltrosReservaDTO filtrosReserva){
        RespuestaGeneralDTO respuesta = iConsultaReservaService.buscarReservas(filtrosReserva);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }

}
