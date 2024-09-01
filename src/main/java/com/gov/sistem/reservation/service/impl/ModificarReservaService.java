package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ReservaServicioDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.entity.embeddable.ReservaServicioId;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.commons.util.mapper.ReservaServicioMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaServicioRepository;
import com.gov.sistem.reservation.service.IModificarReservaService;
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

    private final ReservaServicioRepository reservaServicioRepository;

    private final ReservaServicioMapper reservaServicioMapper;

    private final ServicioNextMapper servicioNextMapper;

    @Override
    @Transactional
    public RespuestaGeneralDTO modificarReserva(ReservaDTO reservaDTO, List<ServicioDTO> servicios) {
        RespuestaGeneralDTO respuestaGeneral = new RespuestaGeneralDTO();
        try{
            reservaRepository.save(reservaMapper.dtoToEntity(reservaDTO));
            List<ReservaServicioDTO> listServicios = reservaServicioMapper.listEntityToListDto(reservaServicioRepository.findAllByReservaServicioId_CodigoReservaFk(reservaDTO.getCodigoReserva()).orElse(null));
            if(listServicios != null && !listServicios.isEmpty()){
                List<ServicioDTO> serviciosAntiguos = servicioNextMapper.listReservaServicioToListServicio(listServicios);
                eliminarReservas(serviciosAntiguos, servicios,reservaDTO.getCodigoReserva());
                agregarServicios(serviciosAntiguos, servicios, reservaDTO.getCodigoReserva());
            }
            respuestaGeneral.setMensaje("Se actualizo correctamente la reserva");
            respuestaGeneral.setError(false);
            respuestaGeneral.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error("Error en modificar la reserva ", ex);
            respuestaGeneral.setMensaje("Error general del sistema");
            respuestaGeneral.setError(true);
            respuestaGeneral.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return respuestaGeneral;
    }

    private void eliminarReservas(List<ServicioDTO> serviciosAntiguos, List<ServicioDTO> serviciosNuevos, String codigoReserva){
        List<ServicioDTO> serviciosElim = serviciosAntiguos.stream().filter(elemento -> !serviciosNuevos.contains(elemento)).toList();
        for(ServicioDTO servicio : serviciosElim){
            reservaServicioRepository.eliminarServicios(servicio.getCodigoServicio(), codigoReserva);
        }
    }

    private void agregarServicios(List<ServicioDTO> serviciosAntiguos, List<ServicioDTO> serviciosNuevos, String codigoReserva){
        List<ServicioDTO> serviciosAgre = serviciosNuevos.stream().filter(elemento -> !serviciosAntiguos.contains(elemento)).toList();
        for(ServicioDTO servicio : serviciosAgre){
            ReservaServicioId reservaServicioId = new ReservaServicioId();
            reservaServicioId.setCodigoServicioFk(servicio.getCodigoServicio());
            reservaServicioId.setCodigoReservaFk(codigoReserva);
            reservaServicioRepository.save(reservaServicioMapper.dtoToEntity(ReservaServicioDTO.builder().reservaServicioId(reservaServicioId).build()));
        }
    }

}
