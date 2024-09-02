package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.entity.AfiliadoEntity;
import com.gov.sistem.reservation.commons.entity.AfiliadoServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AfiliadoServicioRepository  extends JpaRepository<AfiliadoServicioEntity, AfiliadoEntity> {

    @Query("""
        SELECT ae.afiliadoFk, ae.servicioFk, ae.afiliadoServicioId FROM AfiliadoServicioEntity ae where ae.afiliadoFk.tipoAfiliadoFk.idTipoAfiliado = :idTipoAfiliado
    """)
    List<Object[]> informacionAfiliadoServicio(@Param("idTipoAfiliado") Long idTipoAfiliado);

    @Query(value = """
               SELECT ae.afiliadoFk, ae.servicioFk, ae.afiliadoServicioId FROM AfiliadoServicioEntity as ae 
                              where ae.afiliadoServicioId.codigoAfiliado = :codigoAfiliado
    """)
    List<Object[]> informacionAfiliadoCodigo(@Param("codigoAfiliado") String codigoAfiliado);

}
