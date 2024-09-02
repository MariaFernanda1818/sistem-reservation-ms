package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.dto.InputReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.service.ICancelarReservaService;
import com.gov.sistem.reservation.service.ICrearReservaService;
import com.gov.sistem.reservation.service.IModificarReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservaInputControllerTest {

    @InjectMocks
    private ReservaInputController reservaInputController;

    @Mock
    private ICrearReservaService iCrearReservaService;

    @Mock
    private IModificarReservaService iModificarReservaService;

    @Mock
    private ICancelarReservaService iCancelarReservaService;

    @Test
    @DisplayName("Se prueba el controller de crear reserva")
    public void crearPrestamoControllerTest(){
        InputReservaDTO inputReservaDTO = new InputReservaDTO();
        when(iCrearReservaService.crearReserva(inputReservaDTO.getReservaDTO(), inputReservaDTO.getListServicios()))
                .thenReturn(RespuestaGeneralDTO.builder().status(HttpStatus.CREATED).data(MensajesConstants.CREAR_RESERVA).build());
        ResponseEntity<Object> resp = reservaInputController.crearReserva(inputReservaDTO);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.CREATED).data(MensajesConstants.CREAR_RESERVA).build(), resp.getBody());
    }

    @Test
    @DisplayName("Se prueba el controller de modificar reserva")
    public void modificarReserva() {
        InputReservaDTO inputReservaDTO = new InputReservaDTO();
        when(iModificarReservaService.modificarReserva(inputReservaDTO.getReservaDTO(), inputReservaDTO.getListServicios()))
                .thenReturn(RespuestaGeneralDTO.builder().status(HttpStatus.OK).data(MensajesConstants.ACTUALIZACION_RESERVA).build());
        ResponseEntity<Object> resp = reservaInputController.modificarReserva(inputReservaDTO);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).data(MensajesConstants.ACTUALIZACION_RESERVA).build(), resp.getBody());
    }

    @Test
    @DisplayName("Se prueba el controller de cancelar reserva")
    public void cancelarReserva() {
        String codigoReserva = "12312";
        when(iCancelarReservaService.cancerlaReserva(codigoReserva))
                .thenReturn(RespuestaGeneralDTO.builder().status(HttpStatus.OK).data(MensajesConstants.CANCELA_RESERVA).build());
        ResponseEntity<Object> resp = reservaInputController.cancelarReserva(codigoReserva);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).data(MensajesConstants.CANCELA_RESERVA).build(), resp.getBody());
    }

}
