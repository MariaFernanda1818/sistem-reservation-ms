package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.entity.ReservaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, String> {

    @Modifying
    @Query("""
        UPDATE ReservaEntity SET estadoReservaFk.idEstado = :idEstado where codigoReserva = :codigoReserva
    """)
    @Transactional
    void actualizarEstado(@Param("idEstado") Long idEstado, @Param("codigoReserva") String codigoReserv);

    Boolean existsByCodigoReserva(String codigoReserva);

}
