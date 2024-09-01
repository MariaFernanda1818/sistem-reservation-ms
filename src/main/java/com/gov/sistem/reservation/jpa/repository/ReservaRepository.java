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
            SELECT r.codigo_reserva, r.costo_total_reserva
          FROM reservation.reserva r
          INNER JOIN (
              SELECT DISTINCT ON (rs.reserva_fk) rs.reserva_fk, rs.servicio_fk
              FROM reservation.reserva_servicio rs
          ) rs ON r.codigo_reserva = rs.reserva_fk
          INNER JOIN reservation.cliente c ON r.cliente_reserva_fk = c.codigo_cliente
          INNER JOIN reservation.servicio s ON rs.servicio_fk = s.codigo_servicio
          WHERE (:fechaInicio IS NULL OR r.fecha_inicio_reserva =  TO_DATE(:fechaInicio, 'YYYY-MM-DD'))
            AND (:nombreServicio IS NULL OR s.nombre_servicio LIKE '%' || :nombreServicio || '%')
            AND (:codigoCliente IS NULL OR c.codigo_cliente = :codigoCliente)
            AND (:nombreCliente IS NULL or c.nombre_cliente = :nombreCliente)
            """, nativeQuery = true)
    List<Object[]> buscarReservasCliente(@Param("fechaInicio") String fechaInicio,
                                         @Param("nombreServicio") String nombreServicio,
                                         @Param("codigoCliente") String codigoCliente,
                                         @Param("nombreCliente") String nombreCliente);


}
