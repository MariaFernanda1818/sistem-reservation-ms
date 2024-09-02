package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.service.IModificarReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.ServicioNextMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ModificarReservaService implements IModificarReservaService {

    private final ReservaRepository reservaRepository;

    private final ReservaMapper reservaMapper;


    @Override
    @Transactional
    public RespuestaGeneralDTO modificarReserva(ReservaDTO reservaDTO, List<ServicioDTO> servicios) {
        RespuestaGeneralDTO respuestaGeneral = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_ACTUALIZA_RESERVA);
            reservaRepository.save(reservaMapper.dtoToEntity(reservaDTO));
            respuestaGeneral.setMensaje(MensajesConstants.ACTUALIZACION_RESERVA);
            respuestaGeneral.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_MODIFICAR_RESERVA, ex);
            respuestaGeneral.setMensaje(MensajesConstants.ERROR_GENERAL);
            respuestaGeneral.setError(true);
            respuestaGeneral.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuestaGeneral;
    }


}
