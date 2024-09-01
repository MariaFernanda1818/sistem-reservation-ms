package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, String> {
}
