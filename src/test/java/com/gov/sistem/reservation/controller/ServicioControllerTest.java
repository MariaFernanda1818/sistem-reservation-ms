package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import com.gov.sistem.reservation.dto.FiltrosServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.service.IConsultaServicioService;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicioControllerTest {

    @InjectMocks
    private ServiciosController serviciosController;

    @Mock
    private IConsultaServicioService iConsultaServicioService;

    @Test
    @DisplayName("Se prueba el controller de consulta servicios por tipo servicios")
    public void consultaTipoServicio(){
        Long tipoServicioId = 1L;
        when(iConsultaServicioService.consultaServiciosTipoServicio(tipoServicioId)).thenReturn(
                RespuestaGeneralDTO.builder().data(new ArrayList<>()).status(HttpStatus.OK).mensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_SERVICIO).build()
        );
        ResponseEntity<Object> resp = serviciosController.consultaTipoServicio(tipoServicioId);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().data(new ArrayList<>()).status(HttpStatus.OK).mensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_SERVICIO).build(), resp.getBody());
    }

    @Test
    @DisplayName("Se prueba el controller de consulta servicios por filtros")
    public void consultaFiltros() throws IOException {
        FiltrosServicioDTO filtrosServicioDTO = Utilidades.convertJsonToDto(new File("src/test/resources/filtrosServicios.json"), FiltrosServicioDTO.class);
        when(iConsultaServicioService.consultaServiciosFiltros(filtrosServicioDTO)).thenReturn(
                RespuestaGeneralDTO.builder().data(new ArrayList<>()).status(HttpStatus.OK).mensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_FILTROS).build()
        );
        ResponseEntity<Object> resp = serviciosController.consultaFiltros(filtrosServicioDTO);
        Assertions.assertEquals(
                RespuestaGeneralDTO.builder().data(new ArrayList<>()).status(HttpStatus.OK).mensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_FILTROS).build(),
                resp.getBody()
        );
    }

}
