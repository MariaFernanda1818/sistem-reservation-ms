package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.entity.ReservaServicioEntity;
import com.gov.sistem.reservation.commons.entity.embeddable.ReservaServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaServicioRepository extends JpaRepository<ReservaServicioEntity, ReservaServicioId> {

    Optional<List<ReservaServicioEntity>> findAllByReservaServicioId_CodigoReservaFk(String codigoReserva);

    @Modifying
    @Query(value = """
        delete from reservation.reserva_servicio where servicio_fk = :codigoServicio and reserva_fk =:codigoReserva
    """, nativeQuery = true)
    void eliminarServicios(@Param("codigoServicio") String codigoServicio, @Param("codigoReserva")String codigoReserva);

    void deleteAllByReservaFk_CodigoReserva(String codigoReserva);

}
