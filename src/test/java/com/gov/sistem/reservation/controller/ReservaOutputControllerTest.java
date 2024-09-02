package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import com.gov.sistem.reservation.commons.util.mapper.AfiliadoServicioMapper;
import com.gov.sistem.reservation.dto.FiltrosReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.AfiliadoServicioRepository;
import com.gov.sistem.reservation.service.impl.ConsultaReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservaOutputControllerTest {

    @InjectMocks
    private ReservaOutputController reservaOutputController;

    @Mock
    private ConsultaReservaService consultaReservaService;

    @Mock
    private AfiliadoServicioRepository afiliadoServicioRepository;

    @Spy
    private AfiliadoServicioMapper afiliadoServicioMapper;

    @Test
    @DisplayName("Se prueba el controller de consultar reservas con filtros")
    public void consultaReservasFiltros() throws IOException {
        FiltrosReservaDTO filtrosReservaDTO = Utilidades.convertJsonToDto(new File("src/test/resources/filtrosReservas.json"), FiltrosReservaDTO.class);
        when(consultaReservaService.buscarReservas(filtrosReservaDTO))
                .thenReturn(RespuestaGeneralDTO.builder().status(HttpStatus.OK).data(MensajesConstants.CONSULTA_RESERVAS_FILTROS).build());
        ResponseEntity<Object> resp = reservaOutputController.consultarReservasFiltro(filtrosReservaDTO);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).data(MensajesConstants.CONSULTA_RESERVAS_FILTROS).build(), resp.getBody());
    }

}
