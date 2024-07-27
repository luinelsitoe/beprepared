package com.luinel.beprepared.service;

import com.luinel.beprepared.model.City;
import com.luinel.beprepared.model.Province;

import java.util.List;

public interface LocationService {

    List<Province> getAllProvinces();

    List<City> getAllCities();

    List<City> getAllCitiesByProvinceId(Long provinceId);

    Province getProvinceById(Long provinceId);

    City getCityById(Long cityId);
}
