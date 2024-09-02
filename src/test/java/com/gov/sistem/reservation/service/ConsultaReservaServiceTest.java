package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.FiltrosReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.impl.ConsultaReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.ReservaNextMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaReservaServiceTest {

    @InjectMocks
    private ConsultaReservaService consultaReservaService;

    @Mock
    private  ReservaRepository reservaRepository;

    @Spy
    private  ReservaMapper reservaMapper;

    @Spy
    private  ReservaNextMapper reservaNextMapper;

    @Test
    @DisplayName("Se prueba el service de buscar reservas")
    public void buscarReservas() throws IOException {
        FiltrosReservaDTO filtrosReservas = Utilidades.convertJsonToDto(new File("src/test/resources/filtrosReservas.json"), FiltrosReservaDTO.class);
        when(reservaRepository.buscarReservasCliente(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        RespuestaGeneralDTO respuestaGeneralDTO = consultaReservaService.buscarReservas(filtrosReservas);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).mensaje(MensajesConstants.CONSULTA_RESERVAS_FILTROS).data(new ArrayList<>()).build(), respuestaGeneralDTO);
    }
}
