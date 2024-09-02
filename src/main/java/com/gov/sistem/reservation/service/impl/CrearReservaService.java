package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.EstadoDTO;
import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.entity.AfiliadoServicioEntity;
import com.gov.sistem.reservation.commons.util.enums.EstadoEnum;
import com.gov.sistem.reservation.commons.util.enums.InicialesCodEnum;
import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import com.gov.sistem.reservation.commons.util.mapper.AfiliadoServicioMapper;
import com.gov.sistem.reservation.commons.util.mapper.EstadoMapper;
import com.gov.sistem.reservation.commons.util.mapper.ReservaMapper;
import com.gov.sistem.reservation.commons.util.mapper.ServicioMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.AfiliadoServicioRepository;
import com.gov.sistem.reservation.jpa.repository.EstadoRepository;
import com.gov.sistem.reservation.jpa.repository.ReservaRepository;
import com.gov.sistem.reservation.jpa.repository.ServiciosRepository;
import com.gov.sistem.reservation.service.ICrearReservaService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.AfiliadoServiceNextMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CrearReservaService implements ICrearReservaService {

    private final ReservaRepository reservaRepository;

    private final ReservaMapper reservaMapper;

    private final EstadoRepository estadoRepository;

    private final AfiliadoServiceNextMapper afiliadoServiceNextMapper;

    private final AfiliadoServicioRepository afiliadoServicioRepository;

    @Override
    @Transactional
    public RespuestaGeneralDTO crearReserva(ReservaDTO reservaDTO) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            String codigoReserva = generarCodigo();
            log.info(MensajesConstants.INFO_CONSULTA_ESTADO);
            EstadoDTO estado = estadoRepository.findByNombre(EstadoEnum.ACTIVO).orElse(null);
            if(estado == null){
                log.error(MensajesConstants.ERROR_BUSQUEDA_ESTADO);
                respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
                return respuestaGeneralDTO;
            }
            List<AfiliadoServicioEntity> listAfiliados = afiliadoServiceNextMapper.listObjectToListDto(afiliadoServicioRepository.informacionAfiliadoCodigo(reservaDTO.getAfiliadoReservaFk().getCodigoAfiliado()));
            log.info(MensajesConstants.INFO_CONSULTA_AFILIADOS);
            Double total = 0.0;
            for(AfiliadoServicioEntity afiliado : listAfiliados){
                total+=afiliado.getServicioFk().getCostoServicio();
            }
            reservaDTO.setCostoTotalReserva(total);
            reservaDTO.setEstadoReservaFk(estado);
            reservaDTO.setCodigoReserva(codigoReserva);
            reservaDTO.setFechaCreacionReserva(LocalDate.now());
            log.info(MensajesConstants.INFO_CREAR_RESERVA);
            reservaRepository.save(reservaMapper.dtoToEntity(reservaDTO));
            respuestaGeneralDTO.setStatus(HttpStatus.CREATED);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CREAR_RESERVA);
        }catch (Exception e){
            log.error(MensajesConstants.ERROR_CREAR_RESERVA, e);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
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
