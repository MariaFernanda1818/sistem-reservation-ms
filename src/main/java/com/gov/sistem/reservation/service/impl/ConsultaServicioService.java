package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ReservaServicioDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.util.mapper.ReservaServicioMapper;
import com.gov.sistem.reservation.commons.util.mapper.ServicioMapper;
import com.gov.sistem.reservation.dto.FiltrosServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaServicioRepository;
import com.gov.sistem.reservation.jpa.repository.ServiciosRepository;
import com.gov.sistem.reservation.service.IConsultaServicioService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.ReservaNextMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ConsultaServicioService implements IConsultaServicioService {

    private final ServiciosRepository serviciosRepository;

    private final ServicioMapper servicioMapper;

    private final ReservaServicioRepository reservaServicioRepository;

    private final ReservaNextMapper reservaNextMapper;

    @Override
    public RespuestaGeneralDTO consultaServiciosTipoServicio(Long tipoServicio) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_SERVICIOS);
            List<ServicioDTO> listServicios = servicioMapper.listEntityToListDto(serviciosRepository.findAllByTipoServicioFk_IdTipoServicio(
                    tipoServicio
            ));
            respuestaGeneralDTO.setData(listServicios);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_SERVICIO);
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_CONSULTAR_SERVICIOS, ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
        }
        return respuestaGeneralDTO;
    }

    @Override
    public RespuestaGeneralDTO consultaServiciosReserva(String codigoReserva) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_SERVICIOS);
            List<ServicioDTO> listServicios = reservaNextMapper.listReservaServicioToListServicioDto(
                            reservaServicioRepository.findAllByReservaServicioId_CodigoReservaFk(codigoReserva).orElse(null)
            );
            respuestaGeneralDTO.setData(listServicios);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CONSULTA_SERVICIO_CODIGO_RESERVA);
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_CONSULTAR_SERVICIOS, ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
        }
        return respuestaGeneralDTO;
    }

    @Override
    public RespuestaGeneralDTO consultaServiciosFiltros(FiltrosServicioDTO filtros) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_SERVICIOS);
            List<ServicioDTO> listServicios = servicioMapper.listEntityToListDto(
                    serviciosRepository.buscarServiciosFiltros(filtros.getNombreServicio(), filtros.getCostoMin(), filtros.getCostoMax())
            );
            respuestaGeneralDTO.setData(listServicios);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CONSULTA_SERVICIO_TIPO_FILTROS);
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_CONSULTAR_SERVICIOS, ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
        }
        return respuestaGeneralDTO;
    }
}
