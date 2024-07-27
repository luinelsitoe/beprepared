package com.luinel.beprepared.repository;

import com.luinel.beprepared.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findAllByActive(boolean isActive);

    List<Alert> findAllByActiveAndCityId(boolean isActive, long cityId);

    List<Alert> findAllByActiveAndProvinceId(boolean isActive,long provinceId);

    long countByActive(boolean isActive);
}
