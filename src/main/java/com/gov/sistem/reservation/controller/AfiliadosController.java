package com.gov.sistem.reservation.controller;

import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;
import com.gov.sistem.reservation.service.IConsultaAfiliadosService;
import com.gov.sistem.reservation.util.helper.ApiEndpointsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpointsConstants.BASE_AFILIADO)
@RequiredArgsConstructor
public class AfiliadosController {

    private final IConsultaAfiliadosService iConsultaAfiliadosService;

    @GetMapping("consultarAfilTipo")
    public ResponseEntity<Object> consultarAfiliadoTipoAfiliado(@RequestParam("idTipoAfiliado") Long idTipoAfiliado){
        RespuestaGeneralDTO respuesta = iConsultaAfiliadosService.consultarAfiliadosTipoAfiliado(idTipoAfiliado);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }

    @GetMapping("consultarAfilCodigo")
    public ResponseEntity<Object> consultarAfiliadoCodigoAfiliado(@RequestParam("codigoAfiliado") String codigoAfiliado){
        RespuestaGeneralDTO respuesta = iConsultaAfiliadosService.consultarAfiliadoCodigo(codigoAfiliado);
        return new ResponseEntity<>(respuesta,respuesta.getStatus());
    }


}
