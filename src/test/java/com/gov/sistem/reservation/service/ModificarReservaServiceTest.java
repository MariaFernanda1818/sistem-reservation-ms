package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.impl.ModificarReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.ServicioNextMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
public class ModificarReservaServiceTest {

    @InjectMocks
    private ModificarReservaService modificarReservaService;

    @Mock
    private  ReservaRepository reservaRepository;

    @Spy
    private  ReservaMapper reservaMapper;

    @Spy
    private  ServicioNextMapper servicioNextMapper;


    @Test
    @DisplayName("Se prueba el servicio de modificar reserva")
    public void modificarReserva() {
        RespuestaGeneralDTO respuestaGeneralDTO = modificarReservaService.modificarReserva(new ReservaDTO(),new ArrayList<>());
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).mensaje(MensajesConstants.ACTUALIZACION_RESERVA).build(), respuestaGeneralDTO);

    }


}
