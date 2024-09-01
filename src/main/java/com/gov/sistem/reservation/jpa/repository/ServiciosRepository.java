package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.entity.ServicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiciosRepository extends JpaRepository<ServicioEntity, String> {

    List<ServicioEntity> findAllByTipoServicioFk_IdTipoServicio(Long idTipoServicio);

    @Query("""
    SELECT s from ServicioEntity s where (:nombreServicio is null or s.nombreServicio = :nombreServicio) 
    and ((:costoMin is null and :costoMax is null) or s.costoServicio between :costoMin and :costoMax)
    and (:costoMin is null or s.costoServicio >= :costoMin)
    and (:costoMax is null or s.costoServicio <= :costoMax)
    """)
    List<ServicioEntity> buscarServiciosFiltros(@Param("nombreServicio") String nombreServicio, @Param("costoMin") Double costoMin,
                                                @Param("costoMax") Double costoMax);

}
