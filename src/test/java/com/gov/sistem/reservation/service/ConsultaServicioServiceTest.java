package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import com.gov.sistem.reservation.commons.util.mapper.ServicioMapper;
import com.gov.sistem.reservation.dto.FiltrosServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ServiciosRepository;
import com.gov.sistem.reservation.service.impl.ConsultaServicioService;
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
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaServicioServiceTest {

    @InjectMocks
    private ConsultaServicioService consultaServicioService;

    @Mock
    private  ServiciosRepository serviciosRepository;


    @Spy
    private  ServicioMapper servicioMapper;

    @Spy
    private  ReservaNextMapper reservaNextMapper;

    @Test
    @DisplayName("Se prueba el servicio de consultar servicios por tipo de servicio")
    public void consultaServiciosTipoServicio() {
        Long tipoServicio = 1L;
        when(serviciosRepository.findAllByTipoServicioFk_IdTipoServicio(tipoServicio)).thenReturn(new ArrayList<>());
        RespuestaGeneralDTO respuestaGeneralDTO = consultaServicioService.consultaServiciosTipoServicio(tipoServicio);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).mensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_SERVICIO).data(new ArrayList<>()).build(), respuestaGeneralDTO);
    }


    @Test
    @DisplayName("Se prueba el servicio de consultar servicios por filtros")
    public void consultaServiciosFiltros() throws IOException {
        FiltrosServicioDTO filtros = Utilidades.convertJsonToDto(new File("src/test/resources/filtrosServicios.json"), FiltrosServicioDTO.class);
        when(serviciosRepository.buscarServiciosFiltros(filtros.getNombreServicio(), filtros.getCostoMin(), filtros.getCostoMax())).thenReturn(new ArrayList<>());
        RespuestaGeneralDTO respuestaGeneralDTO = consultaServicioService.consultaServiciosFiltros(filtros);
        Assertions.assertEquals(RespuestaGeneralDTO.builder().status(HttpStatus.OK).mensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_FILTROS).data(new ArrayList<>()).build(), respuestaGeneralDTO);
    }
}
