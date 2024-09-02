package com.gov.sistem.reservation.jpa.repository;

import com.gov.sistem.reservation.commons.entity.AfiliadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliadoRepository extends JpaRepository<AfiliadoEntity, String> {



}
