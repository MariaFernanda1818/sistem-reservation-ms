package com.gov.sistem.reservation.service;

import com.gov.sistem.reservation.dto.RespuestaGeneralDTO;

public interface IConsultaAfiliadosService {

    RespuestaGeneralDTO consultarAfiliadosTipoAfiliado(Long idTipoAfiliado);

    RespuestaGeneralDTO consultarAfiliadoCodigo(String codigoAfiliado);


}

