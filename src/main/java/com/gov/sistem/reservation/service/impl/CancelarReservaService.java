package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.EstadoDTO;
import com.gov.sistem.reservation.commons.util.enums.EstadoEnum;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.EstadoRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.ICancelarReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@RequiredArgsConstructor
public class CancelarReservaService implements ICancelarReservaService {

    private final ReservaRepository reservaRepository;
    private final EstadoRepository estadoRepository;

    @Override
    @Transactional
    public RespuestaGeneralDTO cancerlaReserva(String codigoReserva) {
        RespuestaGeneralDTO respuesta = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_ESTADO);
            EstadoDTO estado = estadoRepository.findByNombre(EstadoEnum.CANCELADO).orElse(null);
            if(estado != null) {
                log.info(MensajesConstants.INFO_ACTUALIZA_ESTADO_RESERVA);
                reservaRepository.actualizarEstado(estado.getIdEstado(), codigoReserva);
                respuesta.setMensaje(MensajesConstants.CANCELA_RESERVA);
                respuesta.setStatus(HttpStatus.OK);
            }
        }catch (Exception e){
            log.error(MensajesConstants.ERROR_CANCELAR_RESERVA, e);
            respuesta.setMensaje(MensajesConstants.ERROR_GENERAL);
            respuesta.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }
}
