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
        SELECT ae FROM AfiliadoServicioEntity ae where ae.afiliadoFk.tipoAfiliadoFk.idTipoAfiliado = :idTipoAfiliado
    """)
    List<AfiliadoServicioEntity> informacionAfiliadoServicio(@Param("idTipoAfiliado") Long idTipoAfiliado);

    @Query(value = """
        SELECT * FROM reservation.afiliado_servicio ae inner join reservation.afiliado a on a.codigo_afiliado = ae.afiliado_fk
        where a.codigo_afiliado = :codigoAfiliado
    """, nativeQuery = true)
    List<AfiliadoServicioEntity> informacionAfiliadoCodigo(@Param("codigoAfiliado") String codigoAfiliado);

}
