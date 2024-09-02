package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.commons.dto.AfiliadoDTO;
import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.entity.ReservaEntity;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.impl.ModificarReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.AfiliadoServiceNextMapper;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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

    @Spy
    private AfiliadoServiceNextMapper afiliadoServiceNextMapper;

    @Test
    @DisplayName("Se prueba el servicio de modificar reserva")
    public void modificarReserva() {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setAfiliadoReservaFk(AfiliadoDTO.builder().codigoAfiliado("234").build());
        when(reservaRepository.findById(any())).thenReturn(Optional.of(new ReservaEntity()));
        RespuestaGeneralDTO respuestaGeneralDTO = modificarReservaService.modificarReserva(new ReservaDTO());
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).mensaje(MensajesConstants.ACTUALIZACION_RESERVA).build(), respuestaGeneralDTO);

    }


}
