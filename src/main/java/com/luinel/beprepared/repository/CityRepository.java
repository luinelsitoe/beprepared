package com.luinel.beprepared.repository;

import com.luinel.beprepared.model.City;
import com.sun.jdi.LongValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long > {

    List<City> findByProvinceId(Long provinceId);
}
