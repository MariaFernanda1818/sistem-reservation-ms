package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.commons.dto.EstadoDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.EstadoRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.impl.CancelarReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CancelarReservaServiceTest {

    @InjectMocks
    private CancelarReservaService cancelarReservaService;
    @Mock
    private  ReservaRepository reservaRepository;
    @Mock
    private  EstadoRepository estadoRepository;

    @Test
    @DisplayName("Se prueba el service de cancerla la reserva")
    public void cancelarReserva() {
        String codigo = "32423";
        when(estadoRepository.findByNombre(any())).thenReturn(Optional.of(new EstadoDTO()));
        reservaRepository.actualizarEstado(any(), any());
        Mockito.verify(reservaRepository).actualizarEstado(any(), any());
        RespuestaGeneralDTO respuestaGeneralDTO = cancelarReservaService.cancerlaReserva(codigo);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().data(null).mensaje(MensajesConstants.CANCELA_RESERVA).status(HttpStatus.OK).build(), respuestaGeneralDTO);
    }
}
