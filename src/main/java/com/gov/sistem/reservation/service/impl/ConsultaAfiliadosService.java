package com.gov.sistem.reservation.service.impl;

import com.gov.sistem.reservation.commons.dto.AfiliadoDTO;
import com.gov.sistem.reservation.commons.dto.AfiliadoServicioDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.entity.AfiliadoServicioEntity;
import com.gov.sistem.reservation.commons.util.mapper.AfiliadoServicioMapper;
import com.gov.sistem.reservation.dto.ResponseAfiliadoServicioDTO;
import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.jpa.repository.AfiliadoServicioRepository;
import com.gov.sistem.reservation.service.IConsultaAfiliadosService;
import com.gov.sistem.reservation.util.helper.MensajesConstants;
import com.gov.sistem.reservation.util.mapper.AfiliadoServiceNextMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsultaAfiliadosService implements IConsultaAfiliadosService {

    private final AfiliadoServicioRepository afiliadoServicioRepository;

    private final AfiliadoServicioMapper afiliadoServicioMapper;

    private final AfiliadoServiceNextMapper afiliadoServiceNextMapper;

    @Override
    public RespuestaGeneralDTO consultarAfiliadosTipoAfiliado(Long idTipoAfiliado) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try{
            log.info(MensajesConstants.INFO_CONSULTA_AFILIADOS);
            List<AfiliadoServicioEntity> listAfiliados = afiliadoServiceNextMapper.listObjectToListDto(afiliadoServicioRepository.informacionAfiliadoServicio(idTipoAfiliado));
            List<ServicioDTO> servicioDTOS = new ArrayList<>();
            List<ResponseAfiliadoServicioDTO> responseAfiliadoServicioDTOList = new ArrayList<>();
            String codigoAfiliado = "";
            Integer cantidad = 0;
            for(AfiliadoServicioEntity afiliado : listAfiliados){
                if(codigoAfiliado == ""){
                    codigoAfiliado = afiliado.getAfiliadoFk().getCodigoAfiliado();
                }
                if(afiliado.getAfiliadoFk().getCodigoAfiliado() != codigoAfiliado){
                    ResponseAfiliadoServicioDTO responseAfiliadoServicioDTO = new ResponseAfiliadoServicioDTO();
                    responseAfiliadoServicioDTO.setCodigoAfiliado(codigoAfiliado);
                    responseAfiliadoServicioDTO.setNombreAfiliado(afiliado.getAfiliadoFk().getNombreAfiliado());
                    responseAfiliadoServicioDTO.setServicios(servicioDTOS);
                    responseAfiliadoServicioDTOList.add(responseAfiliadoServicioDTO);
                    codigoAfiliado = afiliado.getAfiliadoFk().getCodigoAfiliado();
                    servicioDTOS = new ArrayList<>();
                }
                ServicioDTO servicioDTO = ServicioDTO.builder().codigoServicio(afiliado.getServicioFk().getCodigoServicio())
                        .nombreServicio(afiliado.getServicioFk().getNombreServicio())
                        .costoServicio(afiliado.getServicioFk().getCostoServicio()).build();
                servicioDTOS.add(servicioDTO);
                cantidad++;
                if(cantidad == listAfiliados.size()){
                    ResponseAfiliadoServicioDTO responseAfiliadoServicioDTO = new ResponseAfiliadoServicioDTO();
                    responseAfiliadoServicioDTO.setCodigoAfiliado(codigoAfiliado);
                    responseAfiliadoServicioDTO.setNombreAfiliado(afiliado.getAfiliadoFk().getNombreAfiliado());
                    responseAfiliadoServicioDTO.setServicios(servicioDTOS);
                    responseAfiliadoServicioDTOList.add(responseAfiliadoServicioDTO);
                }
            }
            respuestaGeneralDTO.setData(responseAfiliadoServicioDTOList);
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
            List<AfiliadoServicioEntity> listAfiliados = afiliadoServiceNextMapper.listObjectToListDto(afiliadoServicioRepository.informacionAfiliadoCodigo(codigoAfiliado));
            log.info(MensajesConstants.INFO_CONSULTA_AFILIADOS);
            String cod = listAfiliados.get(0).getAfiliadoFk().getCodigoAfiliado();
            List<ServicioDTO> servicios = new ArrayList<>();
            for(AfiliadoServicioEntity afiliado : listAfiliados){
                ServicioDTO servicioDTO = new ServicioDTO();
                servicioDTO.setCodigoServicio(afiliado.getServicioFk().getCodigoServicio());
                servicioDTO.setCostoServicio(afiliado.getServicioFk().getCostoServicio());
                servicioDTO.setNombreServicio(afiliado.getServicioFk().getNombreServicio());
                servicios.add(servicioDTO);
            }
            ResponseAfiliadoServicioDTO responseAfiliadoServicioDTO =new ResponseAfiliadoServicioDTO();
            responseAfiliadoServicioDTO.setCodigoAfiliado(cod);
            responseAfiliadoServicioDTO.setServicios(servicios);
            respuestaGeneralDTO.setData(responseAfiliadoServicioDTO);
            respuestaGeneralDTO.setMensaje(MensajesConstants.CONSULTA_AFILIADOS_CODIGO_AFILIADO);
            respuestaGeneralDTO.setStatus(HttpStatus.OK);
        }catch (Exception ex){
            log.error(MensajesConstants.ERROR_CONSULTAR_AFILIADOS, ex);
            respuestaGeneralDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            respuestaGeneralDTO.setMensaje(MensajesConstants.ERROR_GENERAL);
        }
        return respuestaGeneralDTO;    }
}
