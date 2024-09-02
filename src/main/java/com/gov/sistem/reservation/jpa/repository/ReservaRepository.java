package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.entity.ReservaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, String> {

    @Modifying
    @Query("""
                UPDATE ReservaEntity SET estadoReservaFk.idEstado = :idEstado where codigoReserva = :codigoReserva
            """)
    @Transactional
    void actualizarEstado(@Param("idEstado") Long idEstado, @Param("codigoReserva") String codigoReserv);

    Boolean existsByCodigoReserva(String codigoReserva);

    @Query(value = """
         SELECT r.fecha_inicio_reserva, r.codigo_reserva, r.fecha_fin_reserva, r.costo_total_reserva FROM public.reserva as r where 
        (:fechaInicio is null or r.fecha_inicio_reserva = TO_DATE(:fechaInicio, 'YYYY-MM-DD'))
         and (:codigoCliente is null or r.cliente_reserva_fk = :codigoCliente) and r.estado_reserva_fk = 1
            """, nativeQuery = true)
    List<Object[]> buscarReservasCliente(@Param("fechaInicio") String fechaInicio,
                                         @Param("codigoCliente") String codigoCliente);


}
