package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.dto.EstadoDTO;
import com.gov.sistem.reservation.commons.entity.EstadoEntity;
import com.gov.sistem.reservation.commons.util.enums.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long> {

    @Query("""
        SELECT new com.gov.sistem.reservation.commons.dto.EstadoDTO(e.idEstado, e.nombreEstado) FROM EstadoEntity e where e.nombreEstado = :nombre
    """)
    Optional<EstadoDTO> findByNombre(@Param("nombre") EstadoEnum nombre);

}

