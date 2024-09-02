package com.gov.sistem.reservation.util.mapper;

import com.gov.sistem.reservation.commons.dto.AfiliadoDTO;
import com.gov.sistem.reservation.commons.dto.AfiliadoServicioDTO;
import com.gov.sistem.reservation.commons.dto.ReservaDTO;
import com.gov.sistem.reservation.commons.dto.ServicioDTO;
import com.gov.sistem.reservation.commons.entity.AfiliadoEntity;
import com.gov.sistem.reservation.commons.entity.AfiliadoServicioEntity;
import com.gov.sistem.reservation.commons.entity.ServicioEntity;
import com.gov.sistem.reservation.commons.util.helper.Utilidades;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AfiliadoServiceNextMapper {

    default List<AfiliadoServicioEntity> listObjectToListDto(List<Object[]> listData){

        if(listData.isEmpty()) return new ArrayList<>();
        List<AfiliadoServicioEntity> afiliadosService = new ArrayList<>();
        for(Object[] data : listData){
            AfiliadoServicioEntity afiliadoServicioEntity = new AfiliadoServicioEntity();
            afiliadoServicioEntity.setAfiliadoFk((AfiliadoEntity) data[0]);
            afiliadoServicioEntity.setServicioFk((ServicioEntity) data[1]);
            afiliadosService.add(afiliadoServicioEntity);
        }
        return afiliadosService;
    }

}
