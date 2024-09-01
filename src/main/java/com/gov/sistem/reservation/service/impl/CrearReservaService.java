package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.EstadoDTO;
import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ReservaServicioDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.entity.embeddable.ReservaServicioId;
import com.gov.sistem.reservation.commons.util.enums.EstadoEnum;
import com.gov.sistem.reservation.commons.util.enums.InicialesCodEnum;
import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import com.gov.sistem.reservation.commons.util.mapper.EstadoMapper;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.commons.util.mapper.ReservaServicioMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.EstadoRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaServicioRepository;
import com.gov.sistem.reservation.jpa.repository.ServiciosRepository;
import com.gov.sistem.reservation.service.ICrearReservaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CrearReservaService implements ICrearReservaService {

    private final ReservaRepository reservaRepository;

    private final ReservaMapper reservaMapper;

    private final ReservaServicioMapper reservaServicioMapper;

    private final ReservaServicioRepository reservaServicioRepository;

    private final EstadoRepository estadoRepository;

    private final EstadoMapper estadoMapper;

    private final ServiciosRepository serviciosRepository;

    @Override
    @Transactional
    public RespuestaGeneralDTO crearReserva(ReservaDTO reservaDTO, List<ServicioDTO> listServicios) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            String codigoReserva = generarCodigo();
            EstadoDTO estado = estadoRepository.findByNombre(EstadoEnum.ACTIVO).orElse(null);
            if(estado == null){
                log.error("Error en la busqueda del estado");
                respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                respuestaGeneralDTO.setMensaje("Hubo un error en crear la reserva");
                return respuestaGeneralDTO;
            }
            reservaDTO.setEstadoReservaFk(estado);
            reservaDTO.setCodigoReserva(codigoReserva);
            reservaDTO.setFechaCreacionReserva(LocalDate.now());
            log.info("Se creara la reserva");
            reservaMapper.entityToDto(reservaRepository.save(reservaMapper.dtoToEntity(reservaDTO))).getCodigoReserva();
            log.info("Se juntara la reserva con los servicios");
            for(ServicioDTO servicio : listServicios){
                ReservaServicioId ids = new ReservaServicioId();
                ids.setCodigoReservaFk(codigoReserva);
                ids.setCodigoServicioFk(servicio.getCodigoServicio());
                ReservaServicioDTO reservaServicioDTO = ReservaServicioDTO.builder()
                        .reservaServicioId(ids)
                        .reservaFk(ReservaDTO.builder().codigoReserva(codigoReserva).build())
                        .servicioFk(ServicioDTO.builder().codigoServicio(servicio.getCodigoServicio()).build()).build();
                reservaServicioRepository.save(reservaServicioMapper.dtoToEntity(reservaServicioDTO));
            }
            respuestaGeneralDTO.setStatus(HttpStatus.CREATED);
            respuestaGeneralDTO.setData("Se creo correctamente la reserva");
        }catch (Exception e){
            log.error("Error al crear la reserva", e);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje("Hubo un error en crear la reserva");
        }
        return respuestaGeneralDTO;
    }

    private String generarCodigo(){
        String codigo;
        while (true){
            codigo = Utilidades.generarCodigo(InicialesCodEnum.RES);
            if(!reservaRepository.existsByCodigoReserva(codigo)){
                break;
            }
        }
        return codigo;
    }

}
