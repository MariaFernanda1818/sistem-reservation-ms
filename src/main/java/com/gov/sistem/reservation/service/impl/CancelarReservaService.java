package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.EstadoDTO;
import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ReservaServicioDTO;
import com.gov.sistem.reservation.commons.util.enums.EstadoEnum;
import com.gov.sistem.reservation.commons.util.mapper.EstadoMapper;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.EstadoRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaServicioRepository;
import com.gov.sistem.reservation.service.ICancelarReservaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CancelarReservaService implements ICancelarReservaService {

    private final ReservaServicioRepository reservaServicioRepository;
    private final ReservaRepository reservaRepository;
    private final EstadoRepository estadoRepository;

    @Override
    @Transactional
    public RespuestaGeneralDTO cancerlaReserva(String codigoReserva) {
        RespuestaGeneralDTO respuesta = new RespuestaGeneralDTO();
        try{
            EstadoDTO estado = estadoRepository.findByNombre(EstadoEnum.CANCELADO.getDescripcion()).orElse(null);
            if(estado != null) {
                reservaRepository.actualizarEstado(estado.getIdEstado(), codigoReserva);
                reservaServicioRepository.deleteAllByReservaFk_CodigoReserva(codigoReserva);
                respuesta.setMensaje("Se cancelo correctamente la reserva");
                respuesta.setStatus(HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("Error en cancelar la reserva", e);
            respuesta.setMensaje("Error general");
            respuesta.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuesta;
    }
}
