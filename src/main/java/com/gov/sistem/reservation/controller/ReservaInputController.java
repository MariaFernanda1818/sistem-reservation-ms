package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.dto.InputReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.service.ICancelarReservaService;
import com.gov.sistem.reservation.service.ICrearReservaService;
import com.gov.sistem.reservation.service.IModificarReservaService;
import com.gov.sistem.reservation.util.helper.ApiEndpointsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpointsConstants.BASE_INPUT)
@RequiredArgsConstructor
public class ReservaInputController {

    private final ICancelarReservaService ICancelarReservaService;

    private final ICrearReservaService ICrearReservaService;

    private final IModificarReservaService IModificarReservaService;

    @PostMapping(ApiEndpointsConstants.INPUT_CREAR)
    public ResponseEntity<Object> crearReserva(@RequestBody InputReservaDTO inputReservaDTO){
        RespuestaGeneralDTO respuesta = ICrearReservaService.crearReserva(inputReservaDTO.getReservaDTO(), inputReservaDTO.getListServicios());
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }

    @PostMapping(ApiEndpointsConstants.INPUT_MODIFICAR)
    public ResponseEntity<Object> modificarReserva(@RequestBody InputReservaDTO inputReservaDTO){
        RespuestaGeneralDTO respuesta = IModificarReservaService.modificarReserva(inputReservaDTO.getReservaDTO(), inputReservaDTO.getListServicios());
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }

    @DeleteMapping(ApiEndpointsConstants.INPUT_CANCELAR)
    public ResponseEntity<Object> cancelarReserva(@PathVariable("codigoReserva")String codigoReserva){
        RespuestaGeneralDTO respuesta = ICancelarReservaService.cancerlaReserva(codigoReserva);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());

    }


}
