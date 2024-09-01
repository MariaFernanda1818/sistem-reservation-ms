package com.gov.sistem.reservation.util.mapper;

import com.gov.sistem.reservation.commons.dto.ReservaServicioDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicioMapper {

    default List<ServicioDTO> listReservaServicioToListServicio(List<ReservaServicioDTO> listReservaServicio){

            if(listReservaServicio == null || listReservaServicio.isEmpty()) return new ArrayList<>();
            List<ServicioDTO> resp = new ArrayList<>();
            for(ReservaServicioDTO reservaServicioDTO : listReservaServicio){
                ServicioDTO servicioDTO = new ServicioDTO();
                servicioDTO.setCodigoServicio(reservaServicioDTO.getServicioFk().getCodigoServicio());
                servicioDTO.setCostoServicio(reservaServicioDTO.getServicioFk().getCostoServicio());
                servicioDTO.setNombreServicio(reservaServicioDTO.getServicioFk().getNombreServicio());
                servicioDTO.setTipoServicioFk(reservaServicioDTO.getServicioFk().getTipoServicioFk());
                resp.add(servicioDTO);
            }
            return resp;
    }

}
