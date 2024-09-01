package com.gov.sistem.reservation.util.mapper;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ReservaServicioDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.entity.ReservaServicioEntity;
import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaNextMapper {

    default List<ReservaDTO> listObjectToListDto(List<Object[]> listData){

        if(listData.isEmpty()) return new ArrayList<>();
        List<ReservaDTO> reservas = new ArrayList<>();
        for(Object[] data : listData){
            ReservaDTO reserva = new ReservaDTO();
            reserva.setCodigoReserva(Utilidades.checkType(data[0], String.class).orElse(null));
            reserva.setCostoTotalReserva(Utilidades.checkType(data[1], Double.class).orElse(null));
            reservas.add(reserva);
        }
        return reservas;
    }

    default List<ServicioDTO> listReservaServicioToListServicioDto(List<ReservaServicioEntity> listData){

        if(listData.isEmpty()) return new ArrayList<>();
        List<ServicioDTO> servicios = new ArrayList<>();
        for(ReservaServicioEntity data : listData){
            ServicioDTO servicio = new ServicioDTO();
            servicio.setCodigoServicio(data.getServicioFk().getCodigoServicio());
            servicio.setNombreServicio(data.getServicioFk().getNombreServicio());
            servicio.setCostoServicio(data.getServicioFk().getCostoServicio());
            servicios.add(servicio);
        }
        return servicios;
    }

}
