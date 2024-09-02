package com.gov.sistem.reservation.util.mapper;

import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaNextMapper {

    default List<ReservaDTO> listObjectToListDto(List<Object[]> listData){

        if(listData.isEmpty()) return new ArrayList<>();
        List<ReservaDTO> reservas = new ArrayList<>();
        for(Object[] data : listData){
            ReservaDTO reserva = new ReservaDTO();
            reserva.setFechaInicioReserva(Utilidades.checkType(data[0], LocalDate.class).orElse(null));
            reserva.setCodigoReserva(Utilidades.checkType(data[1], String.class).orElse(null));
            reserva.setFechaFinReserva(Utilidades.checkType(data[2], LocalDate.class).orElse(null));
            reserva.setCostoTotalReserva(Utilidades.checkType(data[3], Double.class).orElse(null));
            reservas.add(reserva);
        }
        return reservas;
    }


}
