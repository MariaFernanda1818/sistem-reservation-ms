package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.dto.FiltrosReservaDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaServicioRepository;
import com.gov.sistem.reservation.service.IConsultaReservaService;
import com.gov.sistem.reservation.util.mapper.ReservaNextMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            List<ReservaDTO> listReservas = reservaNextMapper.listObjectToListDto(reservaRepository.buscarReservasCliente(
                    filtrosReservas.getFecha().toString(),
                    filtrosReservas.getNombreServicio(),
                    filtrosReservas.getCodigoCliente()
            ));
            respuestaGeneralDTO.setData(listReservas);
            respuestaGeneralDTO.setMensaje("Se consulto correctamente las reservas con los filtros");
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error("Error al consultar las reservas", ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje("Hubo un error en crear la reserva");
        }
        return respuestaGeneralDTO;
    }
}
