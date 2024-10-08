package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.commons.dto.AfiliadoDTO;
import com.gov.sistem.reservation.commons.dto.EstadoDTO;
import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.util.enums.EstadoEnum;
import com.gov.sistem.reservation.commons.util.mapper.AfiliadoServicioMapper;
import com.gov.sistem.reservation.commons.util.mapper.EstadoMapper;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.AfiliadoServicioRepository;
import com.gov.sistem.reservation.jpa.repository.EstadoRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.impl.CrearReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.AfiliadoServiceNextMapper;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearReservaServiceTest {

    @InjectMocks
    private CrearReservaService crearReservaService;

    @Mock
    private  ReservaRepository reservaRepository;

    @Spy
    private  ReservaMapper reservaMapper;

    @Mock
    private  EstadoRepository estadoRepository;

    @Mock
    private AfiliadoServicioRepository afiliadoServicioRepository;

    @Spy
    private AfiliadoServicioMapper afiliadoServicioMapper;

    @Spy
    private AfiliadoServiceNextMapper afiliadoServiceNextMapper;

    @Spy
    private  EstadoMapper estadoMapper;

    @Test
    @DisplayName("Se prueba el servicio de crear reserva")
    public void crearReserva() {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setAfiliadoReservaFk(AfiliadoDTO.builder().codigoAfiliado("234").build());
       when(estadoRepository.findByNombre(any())).thenReturn(Optional.of(EstadoDTO.builder().idEstado(1L).nombreEstado(EstadoEnum.ACTIVO).build()));
       when(afiliadoServicioRepository.informacionAfiliadoCodigo(any())).thenReturn(new ArrayList<>());
       RespuestaGeneralDTO respuestaGeneralDTO = crearReservaService.crearReserva(reservaDTO);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.CREATED).mensaje(MensajesConstants.CREAR_RESERVA).build(), respuestaGeneralDTO);

    }

}
