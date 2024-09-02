package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.FiltrosReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.IConsultaReservaService;
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
public class ConsultaReservaService implements IConsultaReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final ReservaNextMapper reservaNextMapper;

    @Override
    public RespuestaGeneralDTO buscarReservas(FiltrosReservaDTO filtrosReservas) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_RESERVAS_FILTROS);
            List<ReservaDTO> listReservas = reservaNextMapper.listObjectToListDto(reservaRepository.buscarReservasCliente(
                    Utilidades.checkType(filtrosReservas.getFecha(), String.class).orElse(null),
                    filtrosReservas.getNombreServicio(),
                    filtrosReservas.getCodigoCliente(),
                    filtrosReservas.getNombreCliente()
            ));
            respuestaGeneralDTO.setData(listReservas);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CONSULTA_RESERVAS_FILTROS);
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_CONSULTAR_RESERVAS, ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
        }
        return respuestaGeneralDTO;
    }
}
