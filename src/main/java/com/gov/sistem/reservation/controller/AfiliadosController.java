package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.dto.FiltrosServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.util.helper.ApiEndpointsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpointsConstants.BASE_INPUT)
@RequiredArgsConstructor
public class AfiliadosController {

    @PostMapping(ApiEndpointsConstants.SERVICIO_CONSULTA_FILTROS)
    public ResponseEntity<Object> consultarAfiliadosServicios(){
        RespuestaGeneralDTO respuesta = iConsultaServicioService.consultaServiciosFiltros(filtrosServicioDTO);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }

}
