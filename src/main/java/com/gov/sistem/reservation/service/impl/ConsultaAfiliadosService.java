package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.AfiliadoServicioDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.util.mapper.AfiliadoServicioMapper;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.AfiliadoServicioRepository;
import com.gov.sistem.reservation.service.IConsultaAfiliadosService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsultaAfiliadosService implements IConsultaAfiliadosService {

    private final AfiliadoServicioRepository afiliadoServicioRepository;

    private final AfiliadoServicioMapper afiliadoServicioMapper;

    @Override
    public RespuestaGeneralDTO consultarAfiliadosTipoAfiliado(Long idTipoAfiliado) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_AFILIADOS);
            List<AfiliadoServicioDTO> litAfiliadoServicio = afiliadoServicioMapper.listEntityToListDto(afiliadoServicioRepository.informacionAfiliadoServicio(
                    idTipoAfiliado
            ));
            respuestaGeneralDTO.setData(litAfiliadoServicio);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CONSULTA_AFILIADOS_TIPO_AFILIADO);
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_CONSULTAR_AFILIADOS, ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
        }
        return respuestaGeneralDTO;
    }

    @Override
    public RespuestaGeneralDTO consultarAfiliadoCodigo(String codigoAfiliado) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_AFILIADOS);
            List<AfiliadoServicioDTO> litAfiliadoServicio = afiliadoServicioMapper.listEntityToListDto(
                    afiliadoServicioRepository.informacionAfiliadoCodigo(
                    codigoAfiliado
            ));
            respuestaGeneralDTO.setData(litAfiliadoServicio);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CONSULTA_AFILIADOS_CODIGO_AFILIADO);
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_CONSULTAR_AFILIADOS, ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
        }
        return respuestaGeneralDTO;    }
}
